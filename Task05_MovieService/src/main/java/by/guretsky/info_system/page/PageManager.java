package by.guretsky.info_system.page;

public final class PageManager {
    private PageManager() {
    }

    public static JspPage defineAndGet(final String uri) {
        PageEnum pageEnum = definePage(uri);
        if (pageEnum != null) {
            return createPage(pageEnum);
        }
        return null;
    }

    public static JspPage createPage(final PageEnum pageEnum) {
        return new JspPage(pageEnum.getPageUri(), pageEnum.getAllowRoles());
    }

    private static PageEnum definePage(final String uri) {
        PageEnum[] enums = PageEnum.values();
        for (PageEnum pageEnum : enums) {
            if (pageEnum.getPageUri().equals(uri)) {
                return pageEnum;
            }
        }
        return null;
    }
}
