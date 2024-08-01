package com.pet.care.pc.entitiy.station;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

// @Getter
// @Entity
// @Builder
// @NoArgsConstructor
// @AllArgsConstructor
public class Hospital {

  @Comment("시군코드")
  private String sigunCd;

  @Comment("사업장명")
  private String bizplcNm;

  @Comment("인허가일자")
  private String licensgDe;

  @Comment("인허가취소일자")
  private String bsnStateNm;

  @Comment("영업상태구분코드")
  private String licensgCanclDe;

  @Comment("영업상태명")
  private String bsnStateDivCd;

  @Comment("폐업일자")
  private String clsbizDe;

  @Comment("소재지시설전화번호")
  private String locplcZipCd;

  @Comment("총종업원수")
  private String totEmplyCnt;

  @Comment("축산물가공업구분명")
  private String sfrmprodProcsbizDivNm;

  @Comment("축산업무구분명")
  private String stockrsDutyDivNm;

  @Comment("소재지시설전화번호")
  private String locplcFacltTelno;

  @Comment("소재지면적정보")
  private String locplcArInfo;

  @Comment("소재지지번주소")
  private String refineLotnoAddr;

  @Comment("도로명우편번호")
  private String roadnmZipCd;

  @Comment("소재지도로명주소")
  private String refineRoadnmAddr;

  @Comment("소재지우편번호")
  private String refineZipCd;

  @Comment("WGS84경도")
  private String refineWgs84Logt;

  @Comment("WGS84위도")
  private String refineWgs84Lat;

  @Comment("업태구분명정보")
  private String bizcondDivNmInfo;

  @Comment("X좌표값")
  private String xCrdntVl;

  @Comment("Y좌표값")
  private String yCrdntVl;

  @Comment("축산고유번호")
  private String stockrsIdntfyNo;

  @Comment("권리주체일련번호")
  private String rightMainbdIdntfyNo;
}
