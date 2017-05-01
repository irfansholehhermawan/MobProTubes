package com.example.sholehhermawan.mobproasssessment.data;

import android.net.Uri;
import android.content.ContentResolver;
import android.provider.BaseColumns;

public final class AntrianContract {

    private AntrianContract() {}

    public static final String CONTENT_AUTHORITY = "com.example.sholehhermawan.mobproasssessment";

    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_ANTRIAN = "antrian";

    public static final class Entry implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_ANTRIAN);

        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ANTRIAN;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ANTRIAN;

        public final static String TABLE_NAME = "antrian";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAMA_USER = "name";
        public final static String COLUMN_KELUHAN = "keluhan";
        public final static String COLUMN_KATEGORI = "kategori";
        public final static String COLUMN_NAMA_DOKTER = "dokter";
        public final static String COLUMN_RUANGAN = "ruangan";

        public static final int KATEGORI_UNKNOWN = 0;
        public static final int KATEGORI_UMUM = 1;
        public static final int KATEGORI_ANAK = 2;
        public static final int KATEGORI_KANDUNGAN = 3;
        public static final int KATEGORI_PENYAKITDALAM = 4;
        public static final int KATEGORI_GIGI = 5;

        public static final int NAMADOKTER_Unknown = 0;
        public static final int NAMADOKTER_Bambang = 1;
        public static final int NAMADOKTER_Jack = 2;
        public static final int NAMADOKTER_Indah = 3;
        public static final int NAMADOKTER_Marsini = 4;
        public static final int NAMADOKTER_Ely = 5;

        public static final int RUANGAN_UNKNOWN = 0;
        public static final int RUANGAN_UMUM = 1;
        public static final int RUANGAN_ANAK = 2;
        public static final int RUANGAN_KANDUNGAN = 3;
        public static final int RUANGAN_PENYAKITDALAM = 4;
        public static final int RUANGAN_GIGI = 5;

        public static boolean isValidKategori(int kategori) {
            if (kategori == KATEGORI_UNKNOWN || kategori == KATEGORI_UMUM || kategori == KATEGORI_ANAK ||
                    kategori == KATEGORI_KANDUNGAN || kategori == KATEGORI_PENYAKITDALAM || kategori == KATEGORI_GIGI) {
                return true;
            }
            return false;
        }
        public static boolean isValidDokter(int dokter) {
            if (dokter == NAMADOKTER_Bambang || dokter == NAMADOKTER_Jack || dokter == NAMADOKTER_Indah ||
                    dokter == NAMADOKTER_Marsini || dokter == NAMADOKTER_Ely || dokter == NAMADOKTER_Unknown) {
                return true;
            }
            return false;
        }
        public static boolean isValidRuangan(int ruangan) {
            if (ruangan == RUANGAN_UNKNOWN || ruangan == RUANGAN_UMUM || ruangan == RUANGAN_ANAK ||
                    ruangan == RUANGAN_KANDUNGAN || ruangan == RUANGAN_PENYAKITDALAM || ruangan == RUANGAN_GIGI) {
                return true;
            }
            return false;
        }
    }

}