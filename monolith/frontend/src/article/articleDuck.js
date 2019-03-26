import axios from 'axios';

const initialState = {
  list: [],
};
const ARTICLE_LOADED = 'ARTICLE_LOADED';

export default function articles(state = initialState, action) {
  switch (action.type) {
  case ARTICLE_LOADED:
    return {
      ...state,
      list: action.payload,
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
