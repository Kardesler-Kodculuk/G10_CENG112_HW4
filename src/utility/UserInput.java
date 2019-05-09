package utility;

import java.lang.reflect.Executable;

import internals.*;

public class UserInput {
	private static String getCreator(IMedia media) {
		String creator;
		if (media.mediaType() == "Movie") {
			creator = ((Movie) media).getDirector();
		} else {
			creator = ((Book) media).getAuthor();
		}

		return creator;
	}

	private static IMedia returnByPrice(String maxmin, String type, String creator, IMedia[] sortedArray) {
		switch (maxmin) {
		case "max":
			for (int i = (sortedArray.length - 1); i >= 0; i--) {
				if (sortedArray[i].mediaType().equals(type) && getCreator(sortedArray[i]).equals(creator)) {
					return sortedArray[i];
				}
			}
		case "min":
			for (IMedia media : sortedArray) {
				if (media.mediaType().equals(type) &&  getCreator(media).equals(creator)) {
					return media;
				}
			}
		default:
			return null;
		}
	}
}
