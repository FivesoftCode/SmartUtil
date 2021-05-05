package com.fivesoft.smartutil;

public class HtmlBuilder {

    private StringBuilder html;

    public HtmlBuilder(){
        this.html = new StringBuilder();
    }

    public HtmlBuilder addTag(Tag tag){
        html.append(tag.toString());
        if(!tag.isClosed())
            html.append("\n");
        return this;
    }

    public HtmlBuilder addPlainText(String text){
        html.append(text);
        return this;
    }

    public HtmlBuilder makeEnter(){
        html.append("\n");
        return this;
    }

    public HtmlBuilder addComment(String comment){
        makeEnter();
        html.append("<!--")
                .append(comment)
                .append("-->");
        return this;
    }

    public String toString(){
        return html.toString();
    }

    public static class Tag {

        private StringBuilder attributes;
        private String tag;
        private boolean closed = true;

        public Tag(String tag){
            attributes = new StringBuilder();
            this.tag = tag;
        }

        public Tag addAttribute(String name, String value){
            return addAttributeI(name, value);
        }

        public Tag addAttribute(String name, int value){
            return addAttributeI(name, value);
        }

        public Tag addAttribute(String name, float value){
            return addAttributeI(name, value);
        }

        public Tag addAttribute(String name, double value){
            return addAttributeI(name, value);
        }

        public Tag addAttribute(String name, short value){
            return addAttributeI(name, value);
        }

        public Tag addAttribute(String name, boolean value){
            return addAttributeI(name, value);
        }

        public Tag addAttribute(String name, long value){
            return addAttributeI(name, value);
        }

        public Tag setClosed(boolean b){
            this.closed = b;
            return this;
        }

        public String toString(){
            if(closed)
                if(hasAttributes())
                    return "<".concat(tag).concat(attributes.toString()).concat("/>");
                else
                    return "</".concat(tag).concat(">");
            else
                return "<".concat(tag).concat(attributes.toString()).concat(">");
        }

        public boolean hasAttributes(){
            return !attributes.toString().equals("");
        }

        public boolean isClosed(){
            return closed;
        }

        private Tag addAttributeI(String name, Object value){
            attributes
                    .append(" ")
                    .append(name)
                    .append("=\"")
                    .append(value)
                    .append("\"");
            return this;
        }

    }

}
