package com.web.god.apt;

import javax.lang.model.element.ExecutableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;

/**
 * Created by anson on 2018/8/29.
 */
public class ExecutableElementParseUtil {

    /**
     * ExecutableElement 的解析工具类
     *
     * @param executableElement
     * @return
     */
    public static ExecutableElementBean parseElement(ExecutableElement executableElement) {
        ExecutableElementBean elementBean = new ExecutableElementBean();
        elementBean.methordName = executableElement.getSimpleName().toString();
        if (executableElement.getParameters() != null && executableElement.getParameters().size() > 0) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < executableElement.getParameters().size(); i++) {
                TypeMirror typeMirror = executableElement.getParameters().get(i).asType();
                if (typeMirror instanceof DeclaredType) {
                    builder.append(((DeclaredType) typeMirror).asElement().toString()).append(" ").append(executableElement.getParameters().get(i).getSimpleName()).append(" ");

                } else {
                    builder.append(typeMirror.toString()).append(" ").append(executableElement.getParameters().get(i).getSimpleName()).append(" ");
                }
                if (i != executableElement.getParameters().size() - 1) {
                    builder.append(" , ");
                }
            }
            elementBean.params = builder.toString();
        }
        elementBean.returnType = executableElement.getReturnType().toString();
        return elementBean;
    }

    public static String getReturnType(ExecutableElementBean bean) {
        switch (bean.returnType) {
            case "void":
                return "";
            case "int":
            case "short":
            case "byte":
            case "long":
            case "float":
            case "double":
            case "char":
                return "return 0 ;";
            case "boolean":
                return "return false ;";
            default:
                return "return null ;";
        }
    }
}
