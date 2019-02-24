package com.georlegacy.general.theatrical.guis.handlers.enumeration;

public enum GUIID {
    FIXTURE_FRESNEL("fixture.fresnel", 0),
    ;

    private final String id;
    private final int nid;

    public String getId() {
        return id;
    }

    public int getNid() {
        return nid;
    }

    GUIID(String id, int nid) {
        this.id = id;
        this.nid = nid;
    }

    public static GUIID getByNid(int nid) {
        for (GUIID guiid : values()) {
            if (guiid.getNid() == nid) {
                return guiid;
            }
        }
        throw new IllegalArgumentException("No such GUIID!");
    }

}
