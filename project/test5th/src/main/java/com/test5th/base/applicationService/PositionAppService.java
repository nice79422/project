package com.test5th.base.applicationService;

import java.util.List;

import com.test5th.base.to.PositionBean;

public interface PositionAppService {
    public List<PositionBean> findPositionList();
    
    public PositionBean findPosition(String positionCode);
}
