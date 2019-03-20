package com.test4th.base.applicationService;

import java.util.List;

import com.test4th.base.to.CodeBean;
import com.test4th.base.to.CodeInfoBean;
import com.test4th.base.to.DetailCodeBean;

/*import com.worldMiplatform.base.to.CodeInfoBean;*/

public interface CodeAppService {
    public List<CodeBean> findCodeList(); 
   public void batchCode(CodeInfoBean codeInfoBean);
    public void batchDetailCode(List<DetailCodeBean> detailCodeList);
}
