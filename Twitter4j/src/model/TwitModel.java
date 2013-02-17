package model;

import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import twitter4j.Status;
import twitter4j.Twitter;

/**
 * TwitModel.class
 * 
 * Does all of the calculating for the TwitterLite
 * @author Ben
 */
@SuppressWarnings("serial")
public class TwitModel extends AbstractTableModel{

	private Date date;
	private String loginName;
	private String displayName;
	private int followers;
	private int freinds;
	private String text;
	private StringBuilder wordList;

	private Tweet t;
	private ArrayList<MyTweet> myTweets;
//	private ArrayList<Word> wordCounter;
//	private List<Status> tweets;
//	private ExportUtility export;
	
	private Twitter twitter;
//	private OAuthSignpostClient client;
	private Status status;

	private String[] columnNames = {"Date", "Login Name",
			"Display Name", "Freinds", "Followers"};
	/**
	 * The Constructor for TwitModel()
	 */
	public TwitModel() { 
		myTweets = new ArrayList<MyTweet>();
//		wordCounter = new ArrayList<Word>();
//		twitter = new Twitter();
	}
	/**
	 * Gets the values to be displayed within the Table
	 * @return val;
	 */
	public Object getValueAt(int row, int col){
		Object val = null;
		switch(col){
		case 0:
			val = myTweets.get(row).getCreatedAt(); 
			break;
		case 1:
			val = myTweets.get(row).getLoginName(); 
			break;
		case 2:
			val = myTweets.get(row).getDisplayName();
			break;
		case 3:
			val = myTweets.get(row).getFriendsCount();
			break;
		case 4:
			val = myTweets.get(row).getFollowersCount();
			break;
		}
		return val;
	}
	@Override
	/**
	 * Creates the column Names.
	 * @param - int columm
	 * return - columnNames[column]
	 */
	public String getColumnName(int column) {
		return columnNames[column];
	}
	/**
	 * Adds a tweet to the table from the ArrayList.
	 * @param t - arrayList being passed into the Table
	 */
	public void add(MyTweet t) {
		if (t != null) { 
			myTweets.add(t);
			fireTableRowsInserted(myTweets.size()-1,
					myTweets.size());
		}
	}
	/**
	 * Removes a Tweet from the array List.
	 * @param index - Position of the value
	 */
	public void remove(int index) {
		try {
			myTweets.remove(index);
			fireTableRowsDeleted(index, index);
			return;
		} catch(IndexOutOfBoundsException e) { 
			JOptionPane.showMessageDialog(null , 
				"Invalid Selection",
				"Invalid action",
				JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * Gets the Status of the specified userName.
	 * @param userName - user that is being searched for
	 */
	public void retriveStatus(String userName){

	}
	/**
	 * Gets the status timeline of a particular user.
	 * @param userName - user name being searched
	 */
	public void retriveStatusList(String userName) {

	}
	/**
	 * Gets the size of an Array List.
	 * @return - myTweets.size() - size of myTweets
	 */
	public int getArrayListSize(){
		return myTweets.size();
	}
	/**
	 * Creates the ArrayList Generator.
	 * @param status
	 */
	public void ArrayListGenerator(Status status) {
		date = status.getCreatedAt();
		loginName = status.getUser().getScreenName();
		displayName = status.getUser().getName();
		followers = status.getUser().getFollowersCount();
		freinds = status.getUser().getFriendsCount();
		text = status.getText();
		t = new Tweet(date , loginName , displayName , 
				followers , freinds , text);
		add(t);
	}
	/**
	 * Retrieves the status of the selected index.
	 * @param index - index that is selected
	 * @return - the text of that index.
	 */
	public String retriveDisplayStatus(int index) {
		return myTweets.get(index).getText();	
	}
	/**
	 * getWordSearch gets what is being
	 * searched and produces an arrayList.
	 * @param keyWord - word being searched
	 */
	public void getWordSearch(String keyWord) {

	}
	/**
	 * Gets the phrase that is being searched.
	 * @param keyWord - keyword 
	 * @param phrase - phrase
	 */
	public void getPhraseSearch(String keyWord ,
			String phrase) {

	}
	/**
	 * gets the statues to a user with a specific input.
	 * @param keyWord
	 * @param phrase
	 * @param toUser
	 */
	public void getToUserSearch(String keyWord ,
			String phrase , String toUser) {

	}
	/**
	 * get the status from a user.
	 * @param keyWord
	 * @param phrase
	 * @param fromUser
	 */
	public void getFromUserSearch(String keyWord ,
			String phrase , String fromUser) {

	}
	/**
	 * gets a statuses that meet all parameters.
	 * @param keyWord
	 * @param phrase
	 * @param toUser
	 * @param fromUser
	 */
	public void getAllSearch(String keyWord ,
			String phrase , String toUser , String fromUser) {

	}
	/**
	 * gets keywords from a particular user.
	 * @param keyWord
	 * @param fromUser
	 */
	public void getKeyFromSearch(String keyWord ,
			String fromUser) {

	}
	/**
	 * Gets keywords to a specified user.
	 * @param keyWord
	 * @param toUser
	 */
	public void getKeyToSearch(String keyWord ,
			String toUser) {

	}
	/**
	 * Authenticates a user.
	 * @param userName
	 * @param userKey
	 * @param userSecret
	 * @param token
	 * @param tokenSecret
	 */
	public void authentication(String userName , 
			String userKey , String userSecret , 
			String token , String tokenSecret) {

	}
	/**
	 * Destroys the status of the current user
	 */
	public void destoryStatus() {

	}
	/**
	 * sets a new status for the current user.
	 * @param post
	 */
	public void updateStatus(String post) {

	}
	/**
	 * Gets he column length.
	 * @return columnName.length();
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	/**
	 * gets the rowCount.
	 * @return myTweets.size();
	 */
	@Override
	public int getRowCount() {
		return myTweets.size();
	}

	/**
	 * Sorts a status by Date.
	 */
	public void sortByDate() {

	}
	/**
	 * Sorts a status by login name.
	 */
	public void sortByLogin() {

	}
	/**
	 * sorts statues by friends count.
	 */
	public void sortByFreinds() {

	}
	/**
	 * sorts statuses by followers count.
	 */
	public void sortByFollowers() {

	}
	/**
	 * sorts statuses by display name.
	 */
	public void sortByDisplayName() {

	}
	/**
	 * loads a string a splits it into a bunch of tokens.
	 * @return inputwords
	 */
	public List<String> loadString(){
		return null;
	}
	/**
	 * countWords gets the count of all words.
	 * @param src - gets List<String>
	 * @return wordList
	 */
	public String countWords(List<String> src) {
		return null;
	}
	/**
	 * Generates the word FrequencyList.
	 * @return countWords(words)
	 */
	public String wordFrequencyList(){
		return  null;
	}
	/**
	 * Gets the top trending list.
	 * @return twitter.getTrends();
	 */
	public Object topTrendingList() {
		return null;
	} 
	/**
	 * saves a file as an XML.
	 * @param filename - what the file name will be
	 * @return the true or false if a success or failure
	 */
	public boolean saveAsXML(String filename) {
		return false;

	}
}