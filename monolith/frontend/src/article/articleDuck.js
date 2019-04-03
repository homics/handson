import axios from 'axios';

const initialState = {
  list: [],
  stocks: [],
};
const ARTICLE_LOADED = 'ARTICLE_LOADED';
const STOCK_LOADED = 'STOCK_LOADED';

export default function articles(state = initialState, action) {
  switch (action.type) {
  case ARTICLE_LOADED:
    return {
      ...state,
      list: action.payload,
    };
   case STOCK_LOADED:
    return {
      ...state,
      stocks: action.payload,
    };
  default:
    return state
  }
}

export function fetchArticles() {
  return dispatch =>
    axios
      .get("/mono/internal/articles").then(response =>
        dispatch({
          type: ARTICLE_LOADED,
          payload: response.data,
        })
      );
}

export function fetchArticlesStocks() {
  return dispatch =>
    axios
      .get("/stock/api").then(response =>
        dispatch({
          type: STOCK_LOADED,
          payload: response.data,
        })
      );
}
