package com.his.service;

import com.his.dao.IHistoryDao;
import com.his.dao.IOutpatientRegistrationDao;
import com.his.pojo.History;
import com.his.pojo.MedicalCard;
import com.his.pojo.OutpatientRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * @Author Sbaby
 * @Date 2019/08/20 11:57
 * @Version 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class HistoryService {

    @Autowired
    private IHistoryDao historyDao;
    @Autowired
    private IOutpatientRegistrationDao outpatientRegistrationDao;

    public OutpatientRegistration initHistory(String regId) {
        OutpatientRegistration outpatientRegistration = outpatientRegistrationDao.findById(regId).get();
        History history = new History();
        history.setHistoryId(UUID.randomUUID().toString().replaceAll("-", ""));
        historyDao.save(history);
        return outpatientRegistration;
    }


}
