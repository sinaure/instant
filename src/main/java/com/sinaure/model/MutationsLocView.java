package com.sinaure.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;


/**
 * The persistent class for the mutations_all_view database table.
 * 
 */
@Entity
@Table(name="mutations_loc_view")
public class MutationsLocView {

	private Integer anneemut;

	private String coddep;

	private Integer cod_com;

	private String codservch;

	private Integer codtypbien;

	private String datemut;

	private String point;

	@Id
	private Long idmutation;

	private String idmutinvar;

	private String idnatmut;

	private String idopendata;

	@Column(name="l_artcgi")
	private String lArtcgi;

	@Column(name="l_codinsee")
	@Type(type = "com.sinaure.types.StringArrayType")
	private String[] lCodinsee;

	@Column(name="l_dcnt")
	@Type(type = "com.sinaure.types.StringArrayType")
	private String[] lDcnt;

	@Column(name="l_idlocmut")
	@Type(type = "com.sinaure.types.StringArrayType")
	private String[] lIdlocmut;

	@Column(name="l_idpar")
	@Type(type = "com.sinaure.types.StringArrayType")
	private String[] lIdpar;

	@Column(name="l_idparmut")
	@Type(type = "com.sinaure.types.StringArrayType")
	private String[] lIdparmut;

	@Column(name="l_section")
	@Type(type = "com.sinaure.types.StringArrayType")
	private String[] lSection;

	private String libnatmut;

	private String libtypbien;

	private Integer moismut;

	private Integer nbapt1pp;

	private Integer nbapt2pp;

	private Integer nbapt3pp;

	private Integer nbapt4pp;

	private Integer nbapt5pp;

	private String nbartcgi;

	private Integer nbcomm;

	private Integer nbdispo;

	private Integer nblocact;

	private Integer nblocapt;

	private Integer nblocdep;

	private Integer nblocmai;

	private Integer nblocmut;

	private Integer nblot;

	private Integer nbmai1pp;

	private Integer nbmai2pp;

	private Integer nbmai3pp;

	private Integer nbmai4pp;

	private Integer nbmai5pp;

	private String nbpar;

	private Integer nbparmut;

	private Integer nbsection;

	private Integer nbsuf;

	private Integer nbvolmut;

	private String refdoc;

	private Long sapt1pp;

	private Long sapt2pp;

	private Long sapt3pp;

	private Long sapt4pp;

	private Long sapt5pp;

	private Long sbatact;

	private Long sbatapt;

	private Long sbati;

	private Long sbatmai;

	private Long smai1pp;

	private Long smai2pp;

	private Long smai3pp;

	private Long smai4pp;

	private Long smai5pp;

	private Long sterr;

	private Double valeurfonc;

	private String vefa;

	public MutationsLocView() {
	}

	public Integer getAnneemut() {
		return this.anneemut;
	}

	public void setAnneemut(Integer anneemut) {
		this.anneemut = anneemut;
	}

	public String getCoddep() {
		return this.coddep;
	}

	public void setCoddep(String coddep) {
		this.coddep = coddep;
	}

	public Integer getCod_com() {
		return cod_com;
	}

	public void setCod_com(Integer cod_com) {
		this.cod_com = cod_com;
	}

	public String getCodservch() {
		return this.codservch;
	}

	public void setCodservch(String codservch) {
		this.codservch = codservch;
	}

	public Integer getCodtypbien() {
		return this.codtypbien;
	}

	public void setCodtypbien(Integer codtypbien) {
		this.codtypbien = codtypbien;
	}

	public String getDatemut() {
		return this.datemut;
	}

	public void setDatemut(String datemut) {
		this.datemut = datemut;
	}
	public String getPoint() {
		return this.point;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public Long getIdmutation() {
		return this.idmutation;
	}

	public void setIdmutation(Long idmutation) {
		this.idmutation = idmutation;
	}

	public String getIdmutinvar() {
		return this.idmutinvar;
	}

	public void setIdmutinvar(String idmutinvar) {
		this.idmutinvar = idmutinvar;
	}

	public String getIdnatmut() {
		return this.idnatmut;
	}

	public void setIdnatmut(String idnatmut) {
		this.idnatmut = idnatmut;
	}

	public String getIdopendata() {
		return this.idopendata;
	}

	public void setIdopendata(String idopendata) {
		this.idopendata = idopendata;
	}

	public String getLArtcgi() {
		return this.lArtcgi;
	}

	public void setLArtcgi(String lArtcgi) {
		this.lArtcgi = lArtcgi;
	}

	public String[] getLCodinsee() {
		return this.lCodinsee;
	}

	public void setLCodinsee(String[] lCodinsee) {
		this.lCodinsee = lCodinsee;
	}

	public String[] getLDcnt() {
		return this.lDcnt;
	}

	public void setLDcnt(String[] lDcnt) {
		this.lDcnt = lDcnt;
	}

	public String[] getLIdlocmut() {
		return this.lIdlocmut;
	}

	public void setLIdlocmut(String[] lIdlocmut) {
		this.lIdlocmut = lIdlocmut;
	}

	public String[] getLIdpar() {
		return this.lIdpar;
	}

	public void setLIdpar(String[] lIdpar) {
		this.lIdpar = lIdpar;
	}

	public String[] getLIdparmut() {
		return this.lIdparmut;
	}

	public void setLIdparmut(String[] lIdparmut) {
		this.lIdparmut = lIdparmut;
	}

	public String[] getLSection() {
		return this.lSection;
	}

	public void setLSection(String[] lSection) {
		this.lSection = lSection;
	}

	public String getLibnatmut() {
		return this.libnatmut;
	}

	public void setLibnatmut(String libnatmut) {
		this.libnatmut = libnatmut;
	}

	public String getLibtypbien() {
		return this.libtypbien;
	}

	public void setLibtypbien(String libtypbien) {
		this.libtypbien = libtypbien;
	}

	public Integer getMoismut() {
		return this.moismut;
	}

	public void setMoismut(Integer moismut) {
		this.moismut = moismut;
	}

	public Integer getNbapt1pp() {
		return this.nbapt1pp;
	}

	public void setNbapt1pp(Integer nbapt1pp) {
		this.nbapt1pp = nbapt1pp;
	}

	public Integer getNbapt2pp() {
		return this.nbapt2pp;
	}

	public void setNbapt2pp(Integer nbapt2pp) {
		this.nbapt2pp = nbapt2pp;
	}

	public Integer getNbapt3pp() {
		return this.nbapt3pp;
	}

	public void setNbapt3pp(Integer nbapt3pp) {
		this.nbapt3pp = nbapt3pp;
	}

	public Integer getNbapt4pp() {
		return this.nbapt4pp;
	}

	public void setNbapt4pp(Integer nbapt4pp) {
		this.nbapt4pp = nbapt4pp;
	}

	public Integer getNbapt5pp() {
		return this.nbapt5pp;
	}

	public void setNbapt5pp(Integer nbapt5pp) {
		this.nbapt5pp = nbapt5pp;
	}

	public String getNbartcgi() {
		return this.nbartcgi;
	}

	public void setNbartcgi(String nbartcgi) {
		this.nbartcgi = nbartcgi;
	}

	public Integer getNbcomm() {
		return this.nbcomm;
	}

	public void setNbcomm(Integer nbcomm) {
		this.nbcomm = nbcomm;
	}

	public Integer getNbdispo() {
		return this.nbdispo;
	}

	public void setNbdispo(Integer nbdispo) {
		this.nbdispo = nbdispo;
	}

	public Integer getNblocact() {
		return this.nblocact;
	}

	public void setNblocact(Integer nblocact) {
		this.nblocact = nblocact;
	}

	public Integer getNblocapt() {
		return this.nblocapt;
	}

	public void setNblocapt(Integer nblocapt) {
		this.nblocapt = nblocapt;
	}

	public Integer getNblocdep() {
		return this.nblocdep;
	}

	public void setNblocdep(Integer nblocdep) {
		this.nblocdep = nblocdep;
	}

	public Integer getNblocmai() {
		return this.nblocmai;
	}

	public void setNblocmai(Integer nblocmai) {
		this.nblocmai = nblocmai;
	}

	public Integer getNblocmut() {
		return this.nblocmut;
	}

	public void setNblocmut(Integer nblocmut) {
		this.nblocmut = nblocmut;
	}

	public Integer getNblot() {
		return this.nblot;
	}

	public void setNblot(Integer nblot) {
		this.nblot = nblot;
	}

	public Integer getNbmai1pp() {
		return this.nbmai1pp;
	}

	public void setNbmai1pp(Integer nbmai1pp) {
		this.nbmai1pp = nbmai1pp;
	}

	public Integer getNbmai2pp() {
		return this.nbmai2pp;
	}

	public void setNbmai2pp(Integer nbmai2pp) {
		this.nbmai2pp = nbmai2pp;
	}

	public Integer getNbmai3pp() {
		return this.nbmai3pp;
	}

	public void setNbmai3pp(Integer nbmai3pp) {
		this.nbmai3pp = nbmai3pp;
	}

	public Integer getNbmai4pp() {
		return this.nbmai4pp;
	}

	public void setNbmai4pp(Integer nbmai4pp) {
		this.nbmai4pp = nbmai4pp;
	}

	public Integer getNbmai5pp() {
		return this.nbmai5pp;
	}

	public void setNbmai5pp(Integer nbmai5pp) {
		this.nbmai5pp = nbmai5pp;
	}

	public String getNbpar() {
		return this.nbpar;
	}

	public void setNbpar(String nbpar) {
		this.nbpar = nbpar;
	}

	public Integer getNbparmut() {
		return this.nbparmut;
	}

	public void setNbparmut(Integer nbparmut) {
		this.nbparmut = nbparmut;
	}

	public Integer getNbsection() {
		return this.nbsection;
	}

	public void setNbsection(Integer nbsection) {
		this.nbsection = nbsection;
	}

	public Integer getNbsuf() {
		return this.nbsuf;
	}

	public void setNbsuf(Integer nbsuf) {
		this.nbsuf = nbsuf;
	}

	public Integer getNbvolmut() {
		return this.nbvolmut;
	}

	public void setNbvolmut(Integer nbvolmut) {
		this.nbvolmut = nbvolmut;
	}

	public String getRefdoc() {
		return this.refdoc;
	}

	public void setRefdoc(String refdoc) {
		this.refdoc = refdoc;
	}

	public Long getSapt1pp() {
		return this.sapt1pp;
	}

	public void setSapt1pp(Long sapt1pp) {
		this.sapt1pp = sapt1pp;
	}

	public Long getSapt2pp() {
		return this.sapt2pp;
	}

	public void setSapt2pp(Long sapt2pp) {
		this.sapt2pp = sapt2pp;
	}

	public Long getSapt3pp() {
		return this.sapt3pp;
	}

	public void setSapt3pp(Long sapt3pp) {
		this.sapt3pp = sapt3pp;
	}

	public Long getSapt4pp() {
		return this.sapt4pp;
	}

	public void setSapt4pp(Long sapt4pp) {
		this.sapt4pp = sapt4pp;
	}

	public Long getSapt5pp() {
		return this.sapt5pp;
	}

	public void setSapt5pp(Long sapt5pp) {
		this.sapt5pp = sapt5pp;
	}

	public Long getSbatact() {
		return this.sbatact;
	}

	public void setSbatact(Long sbatact) {
		this.sbatact = sbatact;
	}

	public Long getSbatapt() {
		return this.sbatapt;
	}

	public void setSbatapt(Long sbatapt) {
		this.sbatapt = sbatapt;
	}

	public Long getSbati() {
		return this.sbati;
	}

	public void setSbati(Long sbati) {
		this.sbati = sbati;
	}

	public Long getSbatmai() {
		return this.sbatmai;
	}

	public void setSbatmai(Long sbatmai) {
		this.sbatmai = sbatmai;
	}

	public Long getSmai1pp() {
		return this.smai1pp;
	}

	public void setSmai1pp(Long smai1pp) {
		this.smai1pp = smai1pp;
	}

	public Long getSmai2pp() {
		return this.smai2pp;
	}

	public void setSmai2pp(Long smai2pp) {
		this.smai2pp = smai2pp;
	}

	public Long getSmai3pp() {
		return this.smai3pp;
	}

	public void setSmai3pp(Long smai3pp) {
		this.smai3pp = smai3pp;
	}

	public Long getSmai4pp() {
		return this.smai4pp;
	}

	public void setSmai4pp(Long smai4pp) {
		this.smai4pp = smai4pp;
	}

	public Long getSmai5pp() {
		return this.smai5pp;
	}

	public void setSmai5pp(Long smai5pp) {
		this.smai5pp = smai5pp;
	}

	public Long getSterr() {
		return this.sterr;
	}

	public void setSterr(Long sterr) {
		this.sterr = sterr;
	}

	public Double getValeurfonc() {
		return this.valeurfonc;
	}

	public void setValeurfonc(Double valeurfonc) {
		this.valeurfonc = valeurfonc;
	}

	public String getVefa() {
		return this.vefa;
	}

	public void setVefa(String vefa) {
		this.vefa = vefa;
	}

}