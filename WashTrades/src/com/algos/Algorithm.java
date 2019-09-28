package com.algos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.dao.AlgoDao;
import com.dao.AlgoDaoImpl;
import com.dao.WashDAO;
import com.dao.WashDAOImpl;


public class Algorithm {

	static  int washID=0;

//	public static List<List<Trade>> subsetNetZeroProfit(List<List<Trade>> netZeroVolumeList){
	public static void subsetNetZeroProfit(List<List<Trade>> netZeroVolumeList){
		List<List<Trade>> netZeroProfitList=new ArrayList<List<Trade>>();
		WashDAO washDAO=new WashDAOImpl();
		Wash wash;

		int n=netZeroVolumeList.size();

		float sell;
		float buy;
		float price,netProfit;
		Trader trader = null;
		Broker broker = null;
		Symbol symbol = null;
		int volume;

		for(int i=0;i<n;i++) {
			sell=0.0f;
			buy=0.0f;
			for(int j=0;j<netZeroVolumeList.get(i).size();j++) {

				price=netZeroVolumeList.get(i).get(j).getPrice();
				volume=netZeroVolumeList.get(i).get(j).getVolume();
				trader = netZeroVolumeList.get(i).get(j).getTrader();
				broker = netZeroVolumeList.get(i).get(j).getBroker();
				symbol = netZeroVolumeList.get(i).get(j).getSymbol();

				if(volume < 0) {
					sell+= price*volume;
				}
				else {
					buy+=price*volume;
				}
			}

			netProfit=(float)(-sell/buy);
			if( netProfit>=0.995 && netProfit<=1.005) {
				netZeroProfitList.add(netZeroVolumeList.get(i));
				washID++;

				wash=new Wash(washID,netProfit, 0, symbol, broker, trader,
						netZeroVolumeList.get(i));
				washDAO.AddWash(wash);
			}
		}
//		return netZeroProfitList;
	}
//	public static List<List<Trade>> subsetEqualVolume(List<Trade> tradeList){
	public static void subsetEqualVolume(List<Trade> tradeList){

		WashDAO washDAO=new WashDAOImpl();
		Wash wash;

		List<List<Trade>> netEqualVolumeList=new ArrayList<List<Trade>>();
		List<Trade> future=new ArrayList<>();
		List<Trade> call=new ArrayList<>();
		List<Trade> put=new ArrayList<>();
		Trader trader = null;
		Broker broker = null;
		Symbol symbol = null;

		for(int i=0;i<tradeList.size();i++) {
			Trade trade=tradeList.get(i);
			if(trade.getSecurityType().equals("future"))
				future.add(trade);
			else if(trade.getSecurityType().equals("call"))
				call.add(trade);
			else if(trade.getSecurityType().equals("put"))
				put.add(trade);
			trader = trade.getTrader();
			broker = trade.getBroker();
			symbol = trade.getSymbol();
		}
		
		List<List<Trade>> validSubsets=new ArrayList<List<Trade>>();

		for(int i=0;i<future.size();i++){

			for(int j=0;j<call.size();j++) { 	

				for(int k=0;k<put.size();k++) {

					List<Trade> list=new ArrayList<Trade>();
					list.add(future.get(i));
					list.add(call.get(j));
					list.add(put.get(k));
					validSubsets.add(list);
				}
			}
		}

		for(int i=0;i<validSubsets.size();i++) {
			List<Trade> trades=validSubsets.get(i);
			int vol1=Math.abs(trades.get(0).getVolume());
			int vol2=Math.abs(trades.get(1).getVolume());
			int vol3=Math.abs(trades.get(2).getVolume());

			if((vol1==vol2) && (vol2==vol3)) {
				washID++;
				wash=new Wash(washID,0, 0, symbol, broker, trader,
						trades);
				washDAO.AddWash(wash);
				
				netEqualVolumeList.add(trades);
				
			}
		}
//		return netEqualVolumeList;

	}


	public static List<List<Trade>> subsetNetZeroVolume(List<Trade> tradeList) { 

		List<List<Trade>> netZeroVolumeList=new ArrayList<List<Trade>>();

		int n=tradeList.size();
		int sum=0;

		int totalSubSets = (1 << n);
		for (int i = 1; i < totalSubSets; ++i) { // loop over all possible subsets
			int curSum = 0;
			for (int j = n - 1; j >= 0; --j) {
				if (((i >> j) & 1) > 0) { 
					curSum +=tradeList.get(j).getVolume();
				}
			}

			if (curSum == sum) { 
				List<Trade> tl=new ArrayList<Trade>();
				for (int j = n - 1; j >= 0; --j) {
					if (((i >> j) & 1) > 0) { 
						tl.add(tradeList.get(j));
					}
				}
				netZeroVolumeList.add(tl);
			}
		}
		return netZeroVolumeList;
	}


	public void algo(){

		AlgoDao dao=new AlgoDaoImpl();
		List<List<Trade>> tradesList=new ArrayList<List<Trade>>();
		tradesList=dao.createViews();

		//Creating different maps for different securities from views
		
		List<Trade> futureList;
		List<Trade> equityList;
		List<Trade> callList;
		List<Trade> putList;
		List<Trade> buyEquitySellFutureList;
		List<Trade> triplet1;
		List<Trade> triplet2;

		for(int j=0;j<tradesList.size();j++) {

			futureList=new ArrayList<Trade>();
			equityList=new ArrayList<Trade>();
			callList=new ArrayList<Trade>();
			putList=new ArrayList<Trade>();
			buyEquitySellFutureList = new ArrayList<Trade>();
			triplet1 = new ArrayList<Trade>();
			triplet2 = new ArrayList<Trade>();


			for(int i=0;i< tradesList.get(j).size();i++) {
				Trade t=tradesList.get(j).get(i);				
				String s= t.getSecurityType();

				if(s.equals("equity")) {

					if(t.getVolume() > 0)
						buyEquitySellFutureList.add(t);

					equityList.add(t);
				}

				else if(s.equals("future")) {
					if(t.getVolume()<0) {
						buyEquitySellFutureList.add(t);
						triplet2.add(t);
					}
					else
						triplet1.add(t);

					futureList.add(t);
				}

				else if(s.equals("call")) {
					if(t.getVolume() < 0)
						triplet1.add(t);
					else
						triplet2.add(t);
					callList.add(t);
				}

				else if(s.equals("put")) {
					if(t.getVolume() > 0)
						triplet1.add(t);
					else
						triplet2.add(t);
					putList.add(t);
				}


			}


			//			for(int i=0;i<netZeroVolumeList_future.size();i++) {
			//				System.out.println("Volume Future");
			//				for(int k=0;k<netZeroVolumeList_future.get(i).size();k++)
			//					System.out.println(netZeroVolumeList_future.get(i).get(k).getTradeID());
			//			}
			//		

			//creating list of particular security type with volume margin 0 
			equityList.sort(Comparator.comparingInt(t->t.volume));
			List<List<Trade>> netZeroVolumeList_equity= subsetNetZeroVolume(equityList);
			
			futureList.sort(Comparator.comparingInt(t->t.volume));
			List<List<Trade>> netZeroVolumeList_future= subsetNetZeroVolume(futureList);


			callList.sort(Comparator.comparingInt(t->t.volume));
			List<List<Trade>> netZeroVolumeList_call= subsetNetZeroVolume(callList);


			putList.sort(Comparator.comparingInt(t->t.volume));
			List<List<Trade>> netZeroVolumeList_put= subsetNetZeroVolume(putList);


			buyEquitySellFutureList.sort(Comparator.comparingInt(t->t.volume));
			List<List<Trade>> netZeroVolumeList_equFut = subsetNetZeroVolume(buyEquitySellFutureList);


//			List<List<Trade>> netZeroVolumeList_triplet1 = subsetEqualVolume(triplet1);
			subsetEqualVolume(triplet1);
			
//			List<List<Trade>> netZeroVolumeList_triplet2 = subsetEqualVolume(triplet2);
			subsetEqualVolume(triplet2);

//			for(int i=0;i<netZeroVolumeList_triplet1.size();i++) {
//				Wash w;
//				System.out.println("Triplet 1 WashTrades" );
//				for(int k=0;k<netZeroVolumeList_triplet1.get(i).size();k++) {
//
//					System.out.print(netZeroVolumeList_triplet1.get(i).get(k).getTradeID()+" ");
//				}
//				System.out.println();
//			} 


//			for(int i=0;i<netZeroVolumeList_triplet2.size();i++) {
//				System.out.println("Triplet 2 WashTrades ");
//				for(int k=0;k<netZeroVolumeList_triplet2.get(i).size();k++)
//					System.out.print(netZeroVolumeList_triplet2.get(i).get(k).getTradeID()+" ");
//				System.out.println();
//			}

//          creating lists with volume margin 0 and profit margin 0
//			List<List<Trade>> netZeroProfit_future= subsetNetZeroProfit(netZeroVolumeList_future);
			subsetNetZeroProfit(netZeroVolumeList_future);
			

			//			WashDAO washDao = new WashDAOImpl();
			//			Wash wash;

//			for(int i=0;i<netZeroProfit_future.size();i++) {
//				System.out.println("Future WashTrades ");
//				for(int k=0;k<netZeroProfit_future.get(i).size();k++) {
//
//					//					System.out.println(netZeroProfit_future.get(i).get(k).getTradeID());
//				}
//			}


//			List<List<Trade>> netZeroProfit_equity= subsetNetZeroProfit(netZeroVolumeList_equity);
			subsetNetZeroProfit(netZeroVolumeList_equity);

//			for(int i=0;i<netZeroProfit_equity.size();i++) {
//				System.out.println("Equity WashTrades");
//				for(int k=0;k<netZeroProfit_equity.get(i).size();k++)
//					System.out.print(netZeroProfit_equity.get(i).get(k).getTradeID()+" ");
//				System.out.println();
//			}

//			List<List<Trade>> netZeroProfit_call= subsetNetZeroProfit(netZeroVolumeList_call);
			subsetNetZeroProfit(netZeroVolumeList_call);
//
//			for(int i=0;i<netZeroProfit_call.size();i++) {
//				System.out.println("Call WashTrades");
//				for(int k=0;k<netZeroProfit_call.get(i).size();k++)
//					System.out.println(netZeroProfit_call.get(i).get(k).getTradeID());
//			}

//			List<List<Trade>> netZeroProfit_put= subsetNetZeroProfit(netZeroVolumeList_put);
			subsetNetZeroProfit(netZeroVolumeList_put);

//			for(int i=0;i<netZeroProfit_put.size();i++) {
//				System.out.println("Put WashTrades ");
//				for(int k=0;k<netZeroProfit_put.get(i).size();k++)
//					System.out.println(netZeroProfit_put.get(i).get(k).getTradeID());
//			}

//			List<List<Trade>> netZeroProfit_equFut= subsetNetZeroProfit(netZeroVolumeList_equFut);
			
			subsetNetZeroProfit(netZeroVolumeList_equFut);
//
//			for(int i=0;i<netZeroProfit_equFut.size();i++) {
//				System.out.println("EquityFuture WashTrades");
//				for(int k=0;k<netZeroProfit_equFut.get(i).size();k++)
//					System.out.println(netZeroProfit_equFut.get(i).get(k).getTradeID());
//			}
			//			for(int i=0;i<WashTrades.size();i++) {
			//				washDao.AddWash(WashTrades.get(i));
			//			}






		}
	}



}

