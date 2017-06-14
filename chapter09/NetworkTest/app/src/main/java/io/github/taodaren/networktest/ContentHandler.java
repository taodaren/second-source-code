package io.github.taodaren.networktest;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * SAX 解析
 */

public class ContentHandler extends DefaultHandler {
    private String nodeName;//节点名
    private StringBuilder id;
    private StringBuilder name;
    private StringBuilder version;

    /**
     * XML 解析开始时调用
     */
    @Override
    public void startDocument() throws SAXException {
        id = new StringBuilder();
        name = new StringBuilder();
        version = new StringBuilder();
    }

    /**
     * 开始解析某个节点时调用
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //记录当前节点名
        nodeName = localName;
    }

    /**
     * 获取节点中内容时调用
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        //根据当前的节点名判断将内容添加到哪一个 StringBuilder 对象中
        if ("id".equals(nodeName)) {
            id.append(ch, start, length);
        } else if ("name".equals(nodeName)) {
            name.append(ch, start, length);
        } else if ("version".equals(nodeName)) {
            version.append(ch, start, length);
        }
    }

    /**
     * 完成解析某个节点时调用
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("app".equals(localName)) {
            Log.d("ContentHandler", "id is: " + id.toString().trim());
            Log.d("ContentHandler", "name is: " + name.toString().trim());
            Log.d("ContentHandler", "version is: " + version.toString().trim());
            //最后要将 StringBuilder 清空
            id.setLength(0);
            name.setLength(0);
            version.setLength(0);
        }
    }

    /**
     * 完成整个 XML 解析时调用
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
