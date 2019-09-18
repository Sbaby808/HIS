package com.his.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.his.dao.IMedicalCardDao;
import com.his.dao.IObservationBedDAO;
import com.his.dao.IObservationNoticeDao;
import com.his.dao.IRemainRecordDAO;
import com.his.dao.ISolveSchemeDao;
import com.his.pojo.MedicalCard;
import com.his.pojo.ObservationBed;
import com.his.pojo.ObservationNotice;
import com.his.pojo.RemainRecord;
import com.his.pojo.SolveScheme;
import com.his.utils.UUIDGenerator;

/**  
* @ClassName: ObservationNoticeService  
* @Description: 留观通知Service
* @author Sbaby
* @date 2019年8月23日  下午2:41:48
*    
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class ObservationNoticeService {

	@Autowired
	private IObservationNoticeDao observationNoticeDao;
	@Autowired
	private ISolveSchemeDao schemeDao;
	@Autowired
	private IRemainRecordDAO iRemainRecordDAO;
	@Autowired
	private IObservationBedDAO iObservationBedDAO;
	@Autowired
	private IMedicalCardDao iMedicalCardDao;
	
	/**
	* @Title:addObs
	* @Description:添加或编辑留观通知
	* @param:@param observationNotice
	* @param:@return
	* @return:ObservationNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午2:47:12
	 */
	public ObservationNotice addObs(ObservationNotice observationNotice) {
		if(observationNotice.getObservaId() == null) {
			observationNotice.setObservaId(UUID.randomUUID().toString().replaceAll("-", ""));
		}
		SolveScheme scheme = schemeDao.findById(observationNotice.getSolveScheme().getScheId()).get();
		observationNotice.setSolveScheme(scheme);
		observationNoticeDao.save(observationNotice);
		return observationNotice;
	}
	
	/**
	* @Title:delObsById
	* @Description:根据id删除留观建议
	* @param:@param id
	* @return:void
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午4:57:59
	 */
	public void delObsById(String id) {
		observationNoticeDao.deleteById(id);
	}

	/**
	* @Title:findBySolveId
	* @Description:根据医嘱查询留观通知单
	* @param:@param solveId
	* @param:@return
	* @return:ObservationNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月23日 下午5:08:44
	 */
	public ObservationNotice findBySolveId(String solveId) {
		return observationNoticeDao.getBySolveId(solveId);
	}
	
	/**
	* @Title:findByHistoryId
	* @Description:根据诊断记录变化查询留观通知单
	* @param:@param historyId
	* @param:@return
	* @return:ObservationNotice
	* @throws
	* @author:Sbaby
	* @Date:2019年8月25日 下午1:35:55
	 */
	public ObservationNotice findByHistoryId(String historyId) {
		return observationNoticeDao.getByHistoryId(historyId);
	}
	
	// 查询有多少条记录
		public long findcountss() {
			return iRemainRecordDAO.findcounts();
		}

		// 出院只能直接删除这条记录 只保留正在留观的所有记录
		public void deleteaa(String remainId) {
			// 首先需要置空床位，才能删除留观登记表
			RemainRecord remainRecord = iRemainRecordDAO.findById(remainId).get();
			ObservationBed observationBed = iObservationBedDAO.findById(remainRecord.getJzcwId()).get();
			observationBed.setRemainRecord(null);
			iObservationBedDAO.save(observationBed);
			// 床位置空的时候 直接级联置空了留观登记表 然后直接删除留观登记表即可 不然会级联删除
			remainRecord.setJzcwId(null);
			remainRecord.setObservationBed(null);
			iRemainRecordDAO.deleteById(remainId);
		}

		// 修改remain的状态、、不好用
		public void update(String remainId) {
			RemainRecord remainRecord = iRemainRecordDAO.findById(remainId).get();
			remainRecord.setRemainState("已出院");
			/*
			 * // 出院之后要将对应的床位置空 因为级联更新了 所以 两边直接一起自动置空了 所以先要保存历史记录床位 if
			 * (remainRecord.getHistorybed() != null) { String a =
			 * remainRecord.getHistorybed() + "," +
			 * remainRecord.getObservationBed().getJzcwName();
			 * remainRecord.setHistorybed(a); } else { String a =
			 * remainRecord.getObservationBed().getJzcwName();
			 * remainRecord.setHistorybed(a); }
			 */
			ObservationBed observationBed = iObservationBedDAO.findById(remainRecord.getJzcwId()).get();
			System.out.println(observationBed);
			observationBed.setRemainRecord(null);
			remainRecord.setJzcwId(remainRecord.getJzcwId());
			remainRecord.setObservationBed(observationBed);
			//iObservationBedDAO.save(observationBed);
			//iRemainRecordDAO.save(remainRecord);
		}

		// 新增一个留观登记,一个人可以有多个留观登记，
		// 但是 必须以前的留观登记表状态全部为已出院
		// 传过来床位id 传过来就诊卡id 传过来留观id
		public int addliuguan(String jzcwid, String cardid, String observaId) {
			// 以前登记过的人，直接在上面修改就行了，首先找是不是以前登记过的人
			//if (iRemainRecordDAO.findcountbycardid(cardid) == 0) {
				long a = iRemainRecordDAO.findcount(cardid);
				if (a != 0) {
					System.out.println("不能执行下面的");
					return 0;
				}
				//不管怎么样都增加一个留观登记，即使以前登记过的 也没关系  可以有多条记录
				ObservationNotice observationNotice = observationNoticeDao.findById(observaId).get();
				MedicalCard medicalCard = iMedicalCardDao.findById(cardid).get();
				ObservationBed observationBed = iObservationBedDAO.findById(jzcwid).get();
				// 新增一个留观登记
				RemainRecord remainRecord = new RemainRecord();
				remainRecord.setRemainState("留观中");
				String id = UUIDGenerator.getUUID();
				remainRecord.setRemainId(id);
				remainRecord.setMedicalCard(medicalCard);
				//time要现在的时间
			/* remainRecord.setRemainTime(observationNotice.getObserTime()); */
				remainRecord.setRemainTime(new Date());
				// 急诊床位一对一绑定了 ，两边set？ 瞬时态要先保存 不然要级联更新
				//remainRecord.setJzcwId(jzcwid);
				// 这里set的时候他是空的 observationbed
				//remainRecord.setObservationBed(observationBed);
				iRemainRecordDAO.save(remainRecord);
				// 另一边也绑定
				RemainRecord r1 = iRemainRecordDAO.findById(id).get();
				observationBed.setRemainRecord(r1);
				r1.setJzcwId(jzcwid);
				r1.setObservationBed(observationBed);
				//iObservationBedDAO.save(observationBed);
				return 1;
			//} else {
				// observationNotice = observationNoticeDao.findById(observaId).get();
				//MedicalCard medicalCard = iMedicalCardDao.findById(cardid).get();
				//ObservationBed observationBed = iObservationBedDAO.findById(jzcwid).get();
				// 说明以前有记录，直接在以前上面执行就行 先找到留观登记表
				//这里能找到两条以上的记录吗
				//RemainRecord remainRecord = iRemainRecordDAO.findone(cardid);
				//remainRecord.setRemainState("留观中");
				//remainRecord.setRemainTime(new Date());

				// 急诊床位一对一绑定了 ，两边set？ 瞬时态要先保存 不然要级联更新
				//remainRecord.setJzcwId(jzcwid);
				// 这里set的时候他是空的 observationbed
				//remainRecord.setObservationBed(observationBed);
				//iRemainRecordDAO.save(remainRecord);
				// 另一边也绑定
				//observationBed.setRemainRecord(remainRecord);
				//iObservationBedDAO.save(observationBed);
				//return 1;
			//}
		}

		// 找到一个空闲的床位
		public ObservationBed findonenull() {
			int index = (int) (iObservationBedDAO.findnullbed().size() * Math.random());
			return iObservationBedDAO.findnullbed().get(index);
		}

		// 分页查询所有的留观登记表
		public List<RemainRecord> findrr(int currentpage) {
			PageRequest pageRequest = PageRequest.of(currentpage - 1, 5);

			/*
			 * for (RemainRecord remainRecord : records) { // 被级联了 ，不能在后端这么做 if if
			 * (remainRecord.getObservationBed() == null) { //
			 * observationBed = new ObservationBed();
			 * observationBed.setJzcwId(UUIDGenerator.getUUID());
			 * observationBed.setJzcwName("无");
			 * remainRecord.setObservationBed(observationBed); } }
			 */
			 List<RemainRecord> remainRecords = iRemainRecordDAO.findbypage(pageRequest);
			
			 
			 List<RemainRecord> list = new ArrayList<RemainRecord>(); 
			 for (RemainRecord remainRecord : remainRecords) { 
				 RemainRecord record = new RemainRecord();
			 record = remainRecord;
			 ObservationBed observationBed =iObservationBedDAO.findById(remainRecord.getJzcwId()).get();
			 record.setObservationBed(observationBed); MedicalCard medicalCard = new
			 MedicalCard(); medicalCard =remainRecord.getMedicalCard(); 
			 if(medicalCard.getCountry()==null) { 
				 medicalCard.setCountry(""); 
				 }
			 list.add(record); 
			 }
			 

			return list;
		}

		// 查询所有留观通知单 分页
		public List<ObservationNotice> findobs(int currentpage) {
			PageRequest pageRequest = PageRequest.of(currentpage - 1, 5);
			return observationNoticeDao.findallbypage(pageRequest);
		}

		
		// 查询所有留观通知单数目
		public long findcount() {
			return observationNoticeDao.findcount();
		}



	
}
