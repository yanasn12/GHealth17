package controllers;

import java.util.LinkedList;
import java.util.List;

import common.Op;
import common.Operations;
import entities.Car;
import entities.Cash;
import entities.CreditCard;
import entities.Monthly;
import entities.PaymentInfo;

/**
 * This Class provides methods to manage paymentInfos.
 * 
 * Manages: {@code PaymentInfo}, {@code Cash}, {@code CreditCard}, {@code Monthly}
 * Active is used to efficiently access a specific object that is being edited.
 */
public class PaymentInfoControl {
	
	static List<PaymentInfo> paymentInfos;
	static List<Cash> cashes;
	static List<CreditCard> creditCards;
	static List<Monthly> monthlies;
	
	/**
	 * signifies that this paymentInfo reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static PaymentInfo dummyPaymentInfo = null;
	/**
	 * signifies that this Cash reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static PaymentInfo dummyCash = null;
	/**
	 * signifies that this CreditCard reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static PaymentInfo dummyCreditCard = null;
	/**
	 * signifies that this Monthly reference might exist in the DB but wasn't brought clientside yet.
	 */
	public static PaymentInfo dummyMonthly = null;
	
	/**
	 *  Prepares everything the control needs before it begins to work. Called once in the beginning of the program.
	 */
	public static void initControl ()
	{
		paymentInfos = new LinkedList <PaymentInfo> ();
		cashes = new LinkedList <Cash> ();
		creditCards = new LinkedList <CreditCard> ();
		monthlies = new LinkedList <Monthly> ();
		
		//dummyPaymentInfo = new PaymentInfo();
		//dummyCash = new Cash ();
		//dummyCreditCard = new CreditCard();
		//dummyMonthly = new Monthly ();
	}
	
	// =================================== BUFFERS ==============================
	
	// =================== PAYMENT INFO
	
	/**
 	*  Returns a {@code PaymentInfo} out of the PaymentInfo buffer with a matching id. If the PaymentInfo doesn't exist attempts to fetch it from DB.
 	* @param id  The PaymentInfoID that is searched for.
 	* @return PaymentInfo with matching PaymentInfoID or NULL if User not found.
	*/	
	public static PaymentInfo getPaymentInfoById (int id)	
	{
		for (PaymentInfo paymentInfo : paymentInfos) {
			if (paymentInfo.getPaymentInfoID() == id)
				return paymentInfo;
		}
		return fetchPaymentInfo(id);
	}
	
	/**
	 * Returns a list of paymentInfos after updating it from the DB. 
	 * @return {@code List<PaymentInfo>} paymentInfos buffer.
	 */
	public static List<PaymentInfo> getAllPaymentInfos ()	
	{
		fetchAllPaymentInfos();
		return paymentInfos;
	}
	
	/**
	 *  returns the paymentInfos buffer , recommended to use getAll instead as this method does not updates the buffer from the DB.
	 * @return {@code List<PaymentInfo>} 
	 */
	public static List<PaymentInfo> getPaymentInfos() {
		return paymentInfos;
	}
	
	// ========================= CASH
	
	/**
	 *  returns the Cashs buffer that contains all Cashs. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<Cash> buffer.
	 */
	public static List<Cash> getCashes() {
		return cashes;
	}
	
	/**
	 *  Returns a {@code Cash} out of the {@code Cash} buffer with a matching id. If the entity doesn't exist attempts to fetch it from DB.
	 * @param id  The PaymentInfoID that is searched for.
	 * @return {@code Cash} with matching ID or NULL if entity was not found.
	 */
	public static Cash getCashById(int id){
		
		
		for (Cash cash : cashes)
		{
			if (cash.getPaymentInfoID() == id)
				return cash;
		}
		return fetchCash(id);
	}
	
	/**
	 * Returns a list of all Cashes after updating it from the DB. 
	 * @return {@code List<Cash>} buffer
	 */
	public static List<Cash> getAllCashes(){
		
		fetchAllCashes ();
		return cashes;
	}
	
	// ====================== CREDIT CARD
	
	/**
	 *  returns the CreditCards buffer that contains all CreditCards. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<Cash> buffer.
	 */
	public static List<CreditCard> getCreditCards() {
		return creditCards;
	}
	
	/**
	 *  Returns a {@code CreditCard} out of the {@code CreditCard} buffer with a matching id. If the entity doesn't exist attempts to fetch it from DB.
	 * @param id  The PaymentInfoID that is searched for.
	 * @return {@code CreditCard} with matching ID or NULL if entity was not found.
	 */
	public static CreditCard getCreditCardById(int id){
		
		
		for (CreditCard creditCard : creditCards)
		{
			if (creditCard.getPaymentInfoID() == id)
				return creditCard;
		}
		return fetchCreditCard(id);
	}
	
	/**
	 * Returns a list of all CreditCards after updating it from the DB. 
	 * @return {@code List<CreditCard>} buffer
	 */
	public static List<CreditCard> getAllCreditCards(){
		
		fetchAllCreditCards ();
		return creditCards;
	}
	
	// ====================== MONTHLY
	
	/**
	 *  returns the Monthlies buffer that contains all Monthlies. 
	 *  Recommended to use GetAll instead as this method does not update the buffer from the DB.
	 * @return List<Cash> buffer.
	 */
	public static List<Monthly> getMonthlies() {
		return monthlies;
	}
	
	/**
	 *  Returns a {@code Monthly} out of the {@code Monthly} buffer with a matching id. If the entity doesn't exist attempts to fetch it from DB.
	 * @param id  The PaymentInfoID that is searched for.
	 * @return {@code Monthly} with matching ID or NULL if entity was not found.
	 */
	public static Monthly getMonthlyById(int id){
		
		
		for (Monthly creditCard : monthlies)
		{
			if (creditCard.getPaymentInfoID() == id)
				return creditCard;
		}
		return fetchMonthly(id);
	}
	
	/**
	 * Returns a list of all Monthlies after updating it from the DB. 
	 * @return {@code List<Monthly>} buffer
	 */
	public static List<Monthly> getAllMonthlies(){
		
		fetchAllMonthlies ();
		return monthlies;
	}
	
	// ===================================== DB ACESSS ===============================
	
	// =================== PAYMENT INFO
	
	/**
	 *  Attempts to fetch a {@code PaymentInfo} from DB. Recommended using {@code getPaymentInfoById} instead to check if the user is already in the buffer.
	 * @param id which is looked for
	 * @param method - which search method should be used
	 */
	public static PaymentInfo fetchPaymentInfo (int id)
	{
		PaymentInfo res = null;
		
		Op op = new Op (Operations.GET_PAYMENT_INFO , id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (PaymentInfo) op.getEntity();
		
		if (paymentInfos != null)
		{
			for (PaymentInfo compared : paymentInfos)
				if (compared.getPaymentInfoID() == res.getPaymentInfoID())
					return compared;
				
			paymentInfos.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the PaymentInfo buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllPaymentInfos ()
	{
		List <PaymentInfo> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_PAYMENT_INFO_ALL ,-1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op)LoginCont.client.getMessage();
		res = (List <PaymentInfo>) op.getEntity();
		
		if (paymentInfos != null)
			for (PaymentInfo resS : res)
			{
				addCurrent = true;
				for (PaymentInfo compared : paymentInfos)
					if (compared.getPaymentInfoID() == resS.getPaymentInfoID())
						addCurrent = false;
				
				if (addCurrent)
					paymentInfos.add(resS);
			}
	}
	/**
	 *  Removes the PaymentInfo with the given paymentInfoID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the PaymentInfo to be removed.
	 */
	public static void evictPaymentInfo (int id)
	{
		for (PaymentInfo paymentInfo : paymentInfos)
		{
			if (paymentInfo.getPaymentInfoID() == id)
			{
				Op op = new Op (Operations.UPDATE_PAYMENT_INFO,paymentInfo);
				LoginCont.client.handleMessageFromClientUI(op);
				
				paymentInfos.remove(paymentInfo);
				break;
			}
		}
	}
	
	/**
	 * Updates the PaymentInfo with the given paymentInfoID in the DB.
	 * @param id of the PaymentInfo to be updated
	 */
	public static void updatePaymentInfo (int id)
	{
		for (PaymentInfo paymentInfo : paymentInfos)
		{
			if (paymentInfo.getPaymentInfoID() == id)
			{
				Op op = new Op (Operations.UPDATE_PAYMENT_INFO,paymentInfo);
				LoginCont.client.handleMessageFromClientUI(op);
				break;
			}
		}
	}
	
	// ======================= CASH
	
	/**
	 *  Attempts to fetch a {@code Cash} from DB. Recommended using {@code getCashById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Cash} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Cash fetchCash (int id)
	{
		Cash res = null;
		
		Op op = new Op (Operations.GET_CASH, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Cash) op.getEntity();
		if (res != null)
		{
			for (Cash compared : cashes)
				if (compared.getPaymentInfoID() == res.getPaymentInfoID())
					return compared;
				cashes.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the Cash buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllCashes ()
	{
		List<Cash> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_CASH_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Cash>) op.getEntity();
		if (res != null)
		{
			for (Cash resS : res)
			{
				addCurrent = true;
				for (Cash compared : cashes)
					if (compared.getPaymentInfoID() == resS.getPaymentInfoID())
						addCurrent = false;
				
				if (addCurrent)
					cashes.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the Cash with the given PaymentInfoID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the Cash to be removed.
	 */
	public static void evictCash (int id)
	{
		for (Cash cash : cashes)
		{
			if (cash.getPaymentInfoID() == id)
			{
				Op op = new Op (Operations.UPDATE_CASH,cash);
				LoginCont.client.handleMessageFromClientUI(op);
				
				cashes.remove (cash);
				break;
			}
		}
	}
	
	/**
	 * Updates the Cash with the given PaymentInfoID in the DB.
	 * @param id of the Cash to be updated
	 */
	public static void updateCash (int id)
	{
		for (Cash cash : cashes)
		{
			if (cash.getPaymentInfoID() == id)
			{
				Op op = new Op (Operations.UPDATE_CASH,cash);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given Cash in the DB.
	 * @param car to be created in the DB.
	 */
	public static void createCash (Cash cash)
	{
		Op op = new Op (Operations.CREATE_CASH,cash);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	// ==================== CREDIT CARD
	
	/**
	 *  Attempts to fetch a {@code CreditCard} from DB. Recommended using {@code getCreditCardById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code CreditCard} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static CreditCard fetchCreditCard (int id)
	{
		CreditCard res = null;
		
		Op op = new Op (Operations.GET_CREDIT_CARD, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (CreditCard) op.getEntity();
		if (res != null)
		{
			for (CreditCard compared : creditCards)
				if (compared.getPaymentInfoID() == res.getPaymentInfoID())
					return compared;
				creditCards.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the CreditCard buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllCreditCards ()
	{
		List<CreditCard> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_CREDIT_CARD_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<CreditCard>) op.getEntity();
		if (res != null)
		{
			for (CreditCard resS : res)
			{
				addCurrent = true;
				for (CreditCard compared : creditCards)
					if (compared.getPaymentInfoID() == resS.getPaymentInfoID())
						addCurrent = false;
				
				if (addCurrent)
					creditCards.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the CreditCard with the given PaymentInfoID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the CreditCard to be removed.
	 */
	public static void evictCreditCard (int id)
	{
		for (CreditCard creditCard : creditCards)
		{
			if (creditCard.getPaymentInfoID() == id)
			{
				Op op = new Op (Operations.UPDATE_CREDIT_CARD,creditCard);
				LoginCont.client.handleMessageFromClientUI(op);
				
				creditCards.remove (creditCard);
				break;
			}
		}
	}
	
	/**
	 * Updates the CreditCard with the given PaymentInfoID in the DB.
	 * @param id of the CreditCard to be updated
	 */
	public static void updateCreditCard (int id)
	{
		for (CreditCard creditCard : creditCards)
		{
			if (creditCard.getPaymentInfoID() == id)
			{
				Op op = new Op (Operations.UPDATE_CREDIT_CARD,creditCard);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given CreditCard in the DB.
	 * @param creditCard to be created in the DB.
	 */
	public static void createCreditCard (CreditCard creditCard)
	{
		Op op = new Op (Operations.CREATE_CREDIT_CARD ,creditCard);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
	
	// ======================= MONTHLY
	
	/**
	 *  Attempts to fetch a {@code Monthly} from DB. Recommended using {@code getMonthlyById} instead to check if the entity is already in the buffer.
	 * @param id which is looked for
	 * @return {@code Monthly} from DB with matching id to the given one or NULL if entity not found.
	 */
	public static Monthly fetchMonthly (int id)
	{
		Monthly res = null;
		
		Op op = new Op (Operations.GET_MONTHLY, id);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (Monthly) op.getEntity();
		if (res != null)
		{
			for (Monthly compared : monthlies)
				if (compared.getPaymentInfoID() == res.getPaymentInfoID())
					return compared;
				monthlies.add(res);
		}
		return res;
	}
	
	/**
	 *  updates the Monthly buffer with all entities that didn't exist there.
	 *  In order to refresh the buffer please evict all entities in it first
	 */
	public static void fetchAllMonthlies ()
	{
		List<Monthly> res = null;
		boolean addCurrent;
		
		Op op = new Op (Operations.GET_MONTHLY_ALL, -1);
		LoginCont.client.handleMessageFromClientUI(op);
		op = (Op) LoginCont.client.getMessage();
		res = (List<Monthly>) op.getEntity();
		if (res != null)
		{
			for (Monthly resS : res)
			{
				addCurrent = true;
				for (Monthly compared : monthlies)
					if (compared.getPaymentInfoID() == resS.getPaymentInfoID())
						addCurrent = false;
				
				if (addCurrent)
					monthlies.add(resS);
			}
		}
	}
	
	/**
	 *  Removes the Monthly with the given PaymentInfoID from the buffer, beforehand updates it in the DB and updates all entities that reference it with dummyRefs.
	 * @param id of the Monthly to be removed.
	 */
	public static void evictMonthly (int id)
	{
		for (Monthly monthly : monthlies)
		{
			if (monthly.getPaymentInfoID() == id)
			{
				Op op = new Op (Operations.UPDATE_MONTHLY,monthly);
				LoginCont.client.handleMessageFromClientUI(op);
				
				monthlies.remove (monthly);
				break;
			}
		}
	}
	
	/**
	 * Updates the Monthly with the given PaymentInfoID in the DB.
	 * @param id of the Monthly to be updated
	 */
	public static void updateMonthly (int id)
	{
		for (Monthly monthly : monthlies)
		{
			if (monthly.getPaymentInfoID() == id)
			{
				Op op = new Op (Operations.UPDATE_MONTHLY,monthly);
				LoginCont.client.handleMessageFromClientUI(op);
				
				break;
			}
		}
	}
	
	/**
	 * Creates the given Monthly in the DB.
	 * @param monthly to be created in the DB.
	 */
	public static void createMonthly (Monthly monthly)
	{
		Op op = new Op (Operations.CREATE_MONTHLY, monthly);
		
		LoginCont.client.handleMessageFromClientUI(op);
	}
}
