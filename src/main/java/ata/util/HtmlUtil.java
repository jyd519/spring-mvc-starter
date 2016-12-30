package ata.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;
import org.springframework.web.util.HtmlUtils;

import java.util.List;
import java.util.regex.Pattern;

public class HtmlUtil {
  private static Whitelist whitelist;
  static {
    whitelist = new Whitelist().addTags(new String[]{"a", "b", "blockquote", "br", "caption",
        "cite", "code", "col", "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3",
        "h4", "h5", "h6", "i", "img", "li", "ol", "p", "pre", "q", "small", "span", "strike",
        "strong", "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u",
        "ul"}).addAttributes("a", new String[]{"href", "title"})
        .addAttributes("blockquote", new String[]{"cite"})
        .addAttributes("col", new String[]{"span", "width"})
        .addAttributes("colgroup", new String[]{"span", "width"})
        .addAttributes("img", new String[]{"align", "alt", "src", "title",
            "class", "style", "width", "height", "data-latex"})
        .addAttributes("ol", new String[]{"start", "type"})
        .addAttributes("q", new String[]{"cite"})
        .addAttributes("table", new String[]{"summary", "width"})
        .addAttributes("td", new String[]{"abbr", "axis", "colspan", "rowspan", "width"})
        .addAttributes("th", new String[]{"abbr", "axis", "colspan", "rowspan", "scope", "width"})
        .addAttributes("ul", new String[]{"type"})
        .addAttributes("span", "class")
        .addProtocols("blockquote", "cite", new String[]{"http", "https"})
        .addProtocols("cite", "cite", new String[]{"http", "https"})
        .addProtocols("q", "cite", new String[]{"http", "https"});
  }

  public static String clean(String description) {
    if (description == null || description.isEmpty())
      return description;

    description = Jsoup.clean(description, whitelist);
    org.jsoup.nodes.Document doc = Jsoup.parseBodyFragment(description);
    org.jsoup.nodes.Element body = doc.body();
    List<Node> list = body.childNodes();
    for (int j = list.size() - 1; j >= 0; j--) {
      if (list.get(j).getClass() == TextNode.class) {
        TextNode textNode = (TextNode) list.get(j);
        String text = textNode.getWholeText();
        //删除字符串尾的白字符
        boolean isAllWhiteChar = true;
        for (int i = text.length() - 1; i >= 0; i--) {
          if (text.charAt(i) == 160 || text.charAt(i) == ' ' || text.charAt(i) == '\t'
              || text.charAt(i) == '\n' || text.charAt(i) == '\r') {
            continue;
          } else {
            text = text.substring(0, i + 1);
            isAllWhiteChar = false;
            break;
          }
        }
        if (text.length() == 0 || isAllWhiteChar) {
          list.get(j).remove();
        } else {
          textNode.text(text);
          break;
        }
      } else if (list.get(j).getClass() == Element.class
              && ((Element) list.get(j)).tag().getName().compareToIgnoreCase("p") == 0) {
        Element eTemp = (Element) list.get(j);
        List<Node> pList = eTemp.childNodes();
        boolean isEmpty = true;
        for (int x = pList.size() - 1; x >= 0; x--) {
          if (pList.get(x).getClass() == TextNode.class) {
            TextNode textNode = (TextNode) pList.get(x);
            String text = textNode.getWholeText();
            //删除字符串尾的白字符
            boolean isAllWhiteChar = true;
            for (int i = text.length() - 1; i >= 0; i--) {
              if (text.charAt(i) == 160 || text.charAt(i) == ' ' || text.charAt(i) == '\t'
                  || text.charAt(i) == '\n' || text.charAt(i) == '\r') {
                continue;
              } else {
                text = text.substring(0, i + 1);
                isAllWhiteChar = false;
                break;
              }
            }
            if (text.length() == 0 || isAllWhiteChar) {
              pList.get(x).remove();
            } else {
              textNode.text(text);
              isEmpty = false;
            }
          } else if (pList.get(x).getClass() == Element.class &&
                  ((Element) pList.get(x)).tag().getName().compareToIgnoreCase("br") == 0) {

            pList.get(x).remove();  //删除空回车
          } else {
            isEmpty = false;
          }
          if (!isEmpty) {
            break;
          }
        }
        if (isEmpty) {
          list.get(j).remove();
        } else {
          break;
        }
      } else if (list.get(j).getClass() == Element.class
              && ((Element) list.get(j)).tag().getName().compareToIgnoreCase("br") == 0) {
        //尾部换行
        list.get(j).remove();
      } else {
        break;
      }
    }
    return body.html();
  }

  public static String fixupLinks(String ctxPath, String html) {
    Document doc = Jsoup.parseBodyFragment(html);
    Elements imgs = doc.select("img[src]");
    for (Element element : imgs) {
      String link = element.attr("src");
      if (link.contains("/file/")) {
        String newLink = link.replaceFirst(".*/file/", "");
        element.attr("src", ctxPath + "/file/" + newLink);
      }
    }
    return doc.body().html();
  }

  public static String fixFullLinks(String fullPath, String html) {
    Document doc = Jsoup.parseBodyFragment(html);
    Elements imgs = doc.select("img[src]");
    for (Element element : imgs) {
      String link = element.attr("src");
      element.attr("src", fullPath + link);
    }
    return doc.body().html();
  }

  private final static Pattern reTag = Pattern.compile("</?\\w+[^>]*>");

  public static String htmlToText(String html) {
    html = reTag.matcher(html).replaceAll("");
    return HtmlUtils.htmlUnescape(html);
  }

  public static String htmlToTextWithImage(String html){
    Document doc = Jsoup.parseBodyFragment(html);
    Elements imgs = doc.select("img[src]");
    for (Element element : imgs) {
      String link = element.attr("src");
      element.replaceWith(new Element(Tag.valueOf("span"), "").text(link));
    }
    return doc.body().text();
  }
}
