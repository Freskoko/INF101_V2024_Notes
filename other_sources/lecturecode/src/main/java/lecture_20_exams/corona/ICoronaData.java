package lecture_20_exams.corona;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * This class represents the death tolls related to covid-19 reported by a country.
 * Deaths are reported one day at the time.
 * 
 * In order to use this data with an existing framework for generating plots 
 * we need to be able to output this data in ArrayLists
 * 
 * @author Martin Vatshelle - martin.vatshelle@uib.no
 *
 */

public interface ICoronaData {

	/**
	 * Returns the dates on which coronaDeaths was reported for this data set(country).
	 * These dates shall be reported in order, the earliest day first and the most recent date last.
	 * @return An ArrayList of dates
	 */
	public ArrayList<LocalDate> getDates();
	
	/**
	 * Each day a certain number of deaths is reported, this method returns all reported numbers so far.
	 * The order of the numbers will be the same order as the dates returned by getDates()
	 * @return an ArrayList of daily death count for each day
	 */
	public ArrayList<Integer> getDailyDeaths();
	
	/**
	 * Each ICoronaData series represents a country
	 * @return name of the country
	 */
	public String getCountryName();
	
	/**
	 * The population will be a positive number
	 * @return the population (number of people) of the country
	 */
	public int getPopulation();

	
	/**
	 * For each day return the total number of deaths up until and including that day 
	 * @return an ArrayList containing the cumulative sum of deaths 
	 */
	public ArrayList<Integer> cumulativeDeaths();
	

	/**
	 * As large countries are expected to have more deaths than small countries,
	 * a good measure of how hard hit a country is could be how many deaths per million inhabitants
	 * For each day compute how many deaths (up til and including this day) per million the country has.
	 * 
	 * Example: 
	 * If there is 2 million people and 5 deaths you should return 2.5
	 * If there is 3.5 million people and 7 deaths you should return 2
	 * 
	 * @return a list containing for each day how many deaths there are per million people
	 */
	public ArrayList<Double> deathsPerMillion();


}
