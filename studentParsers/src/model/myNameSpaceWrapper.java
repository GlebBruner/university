package model;

import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

public class myNameSpaceWrapper extends NamespacePrefixMapper {

    private static final String PREFIX = "hos";
    private static final String PREFIX_URI = "http://nure.ua/hostel";
    private static final String XSI_URI = "http://www.w3.org/2001/XMLSchema-instance";
    private static final String XSI = "http://www.w3.org/2001/XMLSchema-instance";


    @Override
    public String[] getPreDeclaredNamespaceUris() {
        return new String[] {PREFIX_URI};
    }

    @Override
    public String getPreferredPrefix(String s, String s1, boolean b) {
        if (PREFIX_URI.equals(s)) {
            return PREFIX;
        }
        return s1;
    }
}
