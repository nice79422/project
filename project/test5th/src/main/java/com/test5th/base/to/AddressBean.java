package com.test5th.base.to;

import com.test5th.common.annotation.Dataset;

@Dataset(name="gds_post")
public class AddressBean{
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