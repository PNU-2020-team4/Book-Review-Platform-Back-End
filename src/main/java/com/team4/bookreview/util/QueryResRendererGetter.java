package com.team4.bookreview.util;

public class QueryResRendererGetter {
	public static DBQueryResRenderer getQueryResRenderer(tableIDs id) {
		DBQueryResRenderer renderer = null;
		switch(id) {
		case USER:
			renderer = new UserQueryResRenderer();
			break;
		case REVIEW:
			renderer = new ReviewQueryResRenderer();
			break;
		case BOOK:
			renderer = new BookQueryResRenderer();
			break;
		case POST:
			renderer = new PostQueryResRenderer();
			break;
		case COMMENTS:
			renderer = new CommentQueryResRenderer();
			break;
		default :
			return null;
		}
		return renderer;	
	}
}
