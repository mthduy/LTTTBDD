package com.example.qlsv;

public class SinhVien {
    private long _id;
    private String _ten;
    private String _lop;

    public SinhVien() {
    }

    public SinhVien(long id, String ten, String lop) {
        this._id = id;
        this._ten = ten;
        this._lop = lop;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String get_ten() {
        return _ten;
    }

    public void set_ten(String _ten) {
        this._ten = _ten;
    }

    public String get_lop() {
        return _lop;
    }

    public void set_lop(String _lop) {
        this._lop = _lop;
    }
}
