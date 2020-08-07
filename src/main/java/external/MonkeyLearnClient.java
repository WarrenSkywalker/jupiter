package external;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.monkeylearn.ExtraParam;
import com.monkeylearn.MonkeyLearn;
import com.monkeylearn.MonkeyLearnException;
import com.monkeylearn.MonkeyLearnResponse;

public class MonkeyLearnClient {
//	convert JSONArray to list of list
/*
 * ["news about tesla....", "news about facebook", "nwes about google...."]
 * JSONArray:
 * {  first for loop iterate the whole jsonarray, the second for loop iterate each item in {}
 * {"keyword" =>"elon musk", "keyword" =>"tesla", rocket}
 * {facebook, mark, social network}
 * {google, brain, machine learning}
 * }
 * 
 * List of list of Strings:
 * <
 * <elon musk, tesla, rocket>,
 * <facebook, mark, social network>,
 * <google, brain, machine learning>
 * >
 */
	private static List<List<String>> getKeywords(JSONArray mlResultArray) {
//		generate empty result
		List<List<String>> topKeywords = new ArrayList<>();
		// Iterate the result array(JSONArray) and convert it to our format(list of list).
		for (int i = 0; i < mlResultArray.size(); ++i) {  //pick each item in the mlResultArray
			List<String> keywords = new ArrayList<>();// output result 
			JSONArray keywordsArray = (JSONArray) mlResultArray.get(i); //input key words
			for (int j = 0; j < keywordsArray.size(); ++j) {
				JSONObject keywordObject = (JSONObject) keywordsArray.get(j);
				// We just need the keyword, excluding other fields.
				String keyword = (String) keywordObject.get("keyword");
				keywords.add(keyword);

			}
			topKeywords.add(keywords);
		}
		return topKeywords;
	}

	public static List<List<String>> extractKeywords(String[] text) {
		if (text == null || text.length == 0) {
			return new ArrayList<>();
		}

		// Use the API key from your account
		MonkeyLearn ml = new MonkeyLearn("5fc8b8253dcd38227012e4038399298d6766e018");

		// Use the keyword extractor. the following is the API format
		ExtraParam[] extraParams = { new ExtraParam("max_keywords", "3") };  //initalization, return an array of extraparam
		MonkeyLearnResponse response;// call API, and API required handling exception, so we must use catch
		try {
			response = ml.extractors.extract("ex_YCya9nrn", text, extraParams);//change to your model id
			JSONArray resultArray = response.arrayResult;
			return getKeywords(resultArray);
		} catch (MonkeyLearnException e) {// it’s likely to have an exception
			e.printStackTrace();
		}
//		when exception happens, we must return sth.
		return new ArrayList<>();
	}


	 public static void main(String[] args) {
			
			String[] textList = {
					"Elon Musk has shared a photo of the spacesuit designed by SpaceX. This is the second image shared of the new design and the first to feature the spacesuit’s full-body look.", };
			List<List<String>> words = extractKeywords(textList);
			for (List<String> ws : words) {//loop 1 times.(only 1 document"")
				for (String w : ws) {// loop 3 times(we set max number of keyword is 3)
					System.out.println(w);
				}
				System.out.println();
			}
		}

	
	
}
