package com.gwwwwt.elements.impl.constantpool;

import com.gwwwwt.Contants.ConstantPoolTag;
import com.gwwwwt.elements.impl.constantpool.elements.ConstantPoolElement;
import com.gwwwwt.elements.DefaultElementImpl;
import com.gwwwwt.parser.DataGenerator;

import java.util.ArrayList;
import java.util.List;

public class ConstantPool extends DefaultElementImpl {

    private int constant_pool_count;
    private List<ConstantPoolElement> elements;
    @Override
    public boolean isFixedSize() {
        return false;
    }

    @Override
    public String print() {
        StringBuilder builder = new StringBuilder();
        builder.append("Size : " + constant_pool_count + "\n");

        for(ConstantPoolElement e : elements) {
            if(null == e) {
                continue;
            }
            builder.append(e.print() + "\n");
        }

        return builder.toString();
    }

    @Override
    protected void processDynamic(DataGenerator generator) {
        this.constant_pool_count = this.content.length;
        elements = new ArrayList<>(constant_pool_count);

        /*
        constant_pool_count的值等于常量池表中的成员数加1。 索引值只有在大于0且小于constant_pool_count时是有效的
         */
        elements.add(0, null);

        /*
        像CONSTANT_CLASS_TAG等，其中会包含一些指向常量池中其他UTF8元素的索引，
        初始读取时也只是一个索引值，为了后面显示更直观一些，设置了下面两个list，
        用于在循环读取常量池元素后，根据里面的索引值指向正确的实例，
         */
        List<ConstantPoolElement> needLink = new ArrayList<>();
        List<ConstantPoolElement> needTwoLinks = new ArrayList<>();

        for(int i = 1; i < constant_pool_count; i++) {
            try {
                ConstantPoolElement element = ConstantPoolElement.getNextElementInstance(generator, i);
                elements.add(i, element);

                ConstantPoolTag tag = element.getTag();
                if(tag ==  ConstantPoolTag.CONSTANT_CLASS_TAG
                        || tag == ConstantPoolTag.CONSTANT_STRING_TAG
                        || tag == ConstantPoolTag.CONSTANT_METHODHANDLE_TAG
                        || tag == ConstantPoolTag.CONSTANT_METHODTYPE_TAG) {
                    needLink.add(element);
                }

                if( tag == ConstantPoolTag.CONSTANT_NAMEANDTYPE_TAG
                        || tag == ConstantPoolTag.CONSTANT_FIELDREF_TAG
                        || tag == ConstantPoolTag.CONSTANT_METHODREF_TAG
                        || tag == ConstantPoolTag.CONSTANT_INTERFACEMETHODREF_TAG) {
                    needTwoLinks.add(element);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for(ConstantPoolElement e : needLink) {
            e.link2other(elements.get(e.getLinkTargetIndex()[0]));
        }

        for(ConstantPoolElement e : needTwoLinks) {
            e.link2others(new ConstantPoolElement[]{elements.get(e.getLinkTargetIndex()[0]), elements.get(e.getLinkTargetIndex()[1])});
        }
    }
}
