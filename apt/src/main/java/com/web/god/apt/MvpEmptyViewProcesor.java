package com.web.god.apt;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import com.web.god.annotation.MvpEmptyViewFactory;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.ElementFilter;

import static javax.lang.model.element.Modifier.FINAL;
import static javax.lang.model.element.Modifier.PUBLIC;
import static javax.lang.model.element.Modifier.STATIC;

/**
 * Created by anson on 2019/5/3.
 */
public class MvpEmptyViewProcesor {

    String CLASS_NAME = "MvpEmptyViewFactory";

    public void process(RoundEnvironment roundEnv, MvpProccesor processor) {
        try {
            TypeSpec.Builder tb = TypeSpec.classBuilder(CLASS_NAME).addModifiers(PUBLIC, FINAL).addJavadoc("empty view by apt");
            MethodSpec.Builder methodBuilder1 = MethodSpec.methodBuilder("create")
                    .returns(Object.class).addModifiers(PUBLIC, STATIC).addException(IllegalAccessException.class).addException(InstantiationException.class)
                    .addParameter(Class.class, "mClass");
            List<ClassName> mList = new ArrayList<>();
            CodeBlock.Builder blockBuilder = CodeBlock.builder();

            blockBuilder.beginControlFlow(" switch (mClass.getCanonicalName())");

            for (TypeElement element : ElementFilter.typesIn(roundEnv.getElementsAnnotatedWith(MvpEmptyViewFactory.class))) {
                ClassName currentType = ClassName.get(element);
                if (mList.contains(currentType)){
                    continue;
                }
                mList.add(currentType);
                //生成本身的方法
                StringBuilder s = new StringBuilder();
                List<? extends Element> enclosedElements = element.getEnclosedElements();
                for (int i = 0; i < enclosedElements.size(); i++) {
                    if (enclosedElements.get(i) instanceof ExecutableElement) {
                        ExecutableElementBean elementBean = ExecutableElementParseUtil.parseElement((ExecutableElement) enclosedElements.get(i));
                        s.append("@Override ").append("public").append(" ").append(elementBean.returnType).append(" ").append(elementBean.methordName).append("(").append(elementBean.params).append(")").append(String.format("{%s}\n", ExecutableElementParseUtil.getReturnType(elementBean)));
                    }
                }
                //生成父类的接口方法(这是一个递归的操作)
                getSuperFun(element, s);
                blockBuilder.addStatement("case $S : \n return  new $T(){ \n$L }", element.toString(), currentType, s);
            }

            blockBuilder.addStatement("default: return null");
            blockBuilder.endControlFlow();
            methodBuilder1.addCode(blockBuilder.build());
            tb.addMethod(methodBuilder1.build());
            JavaFile javaFile = JavaFile.builder("today.information.mvp", tb.build()).build();
            javaFile.writeTo(processor.mFiler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归获取 父类的方法
     *
     * @param element
     * @param s
     */
    private void getSuperFun(TypeElement element, StringBuilder s) {
        List<? extends TypeMirror> interfaces = element.getInterfaces();
        if (interfaces != null && interfaces.size() > 0) {
            for (int i = 0; i < interfaces.size(); i++) {
                TypeMirror typeMirror = interfaces.get(i);
                if (typeMirror instanceof DeclaredType) {
                    Element element1 = ((DeclaredType) typeMirror).asElement();
                    List<? extends Element> innerElements = element1.getEnclosedElements();
                    for (int j = 0; j < innerElements.size(); j++) {
                        if (innerElements.get(i) instanceof ExecutableElement) {
                            ExecutableElementBean elementBean = ExecutableElementParseUtil.parseElement((ExecutableElement) innerElements.get(j));
                            s.append("@Override ").append("public").append(" ").append(elementBean.returnType).append(" ").append(elementBean.methordName).append("(").append(elementBean.params).append(")").append(String.format("{%s}\n", ExecutableElementParseUtil.getReturnType(elementBean)));
                        }
                    }
                    if (element1 instanceof TypeElement) {
                        getSuperFun((TypeElement) element1, s);
                    }
                }
            }
        }
    }
}
