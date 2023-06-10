package com.webtoeic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webtoeic.jpautil.VaiTroTypeConverter;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Blog {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private int id;

	@Column(name = "tieude")
	private String tieude;

	@Column(name = "hinhanh")
	private String hinhanh;

	@Column(name = "mota")
	private String mota;

	@Column(columnDefinition = "TEXT",name="noidung_HTML")
	private String noidung_HTML;

	@Column(columnDefinition = "TEXT",name="noidung_MarkDown")
	private String noidung_MarkDown;

	@Column(name = "ngaydang")
	private Timestamp ngaydang;

	@Column(name = "luotxem")
	private int luotxem;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTieude() {
		return tieude;
	}

	public void setTieude(String tieude) {
		this.tieude = tieude;
	}

	public String getHinhanh() {
		return hinhanh;
	}

	public void setHinhanh(String hinhanh) {
		this.hinhanh = hinhanh;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getNoidung_HTML() {
		return noidung_HTML;
	}

	public void setNoidung_HTML(String noidung_HTML) {
		this.noidung_HTML = noidung_HTML;
	}

	public String getNoidung_MarkDown() {
		return noidung_MarkDown;
	}

	public void setNoidung_MarkDown(String noidung_MarkDown) {
		this.noidung_MarkDown = noidung_MarkDown;
	}

	public Timestamp getNgaydang() {
		return ngaydang;
	}

	public void setNgaydang(Timestamp ngaydang) {
		this.ngaydang = ngaydang;
	}

	public int getLuotxem() {
		return luotxem;
	}

	public void setLuotxem(int luotxem) {
		this.luotxem = luotxem;
	}

	public Blog() {
	}

	public Blog(int id, String tieude, String hinhanh, String mota, String noidung_HTML, String noidung_MarkDown, Timestamp ngaydang, int luotxem) {
		this.id = id;
		this.tieude = tieude;
		this.hinhanh = hinhanh;
		this.mota = mota;
		this.noidung_HTML = noidung_HTML;
		this.noidung_MarkDown = noidung_MarkDown;
		this.ngaydang = ngaydang;
		this.luotxem = luotxem;
	}
}
