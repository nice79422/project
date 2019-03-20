package kr.co.seoulit.sys.to;

import kr.co.seoulit.common.annotation.Dataset;

@Dataset(name="ds_post")
public class PostTO{
   String zipNO, lnmAdres, rnAdres;

   public String getZipNO() {
      return zipNO;
   }

   public void setZipNO(String zipNO) {
      this.zipNO = zipNO;
   }

   public String getLnmAdres() {
      return lnmAdres;
   }

   public void setLnmAdres(String lnmAdres) {
      this.lnmAdres = lnmAdres;
   }

   public String getRnAdres() {
      return rnAdres;
   }

   public void setRnAdres(String rnAdres) {
      this.rnAdres = rnAdres;
   }
}