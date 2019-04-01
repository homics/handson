import axios from 'axios';

const initialState = {
  orderCount: 0,
  avgCart: 0,
};

const STATS_LOADED = 'ORDERS_LOADED';

export default function stats(state = initialState, action) {
  switch (action.type) {
    case STATS_LOADED:
      return {
        ...state,
        orderCount: action.payload.nbOrder,
        avgCart: action.payload.avgPrice,
      };

    default:
      return state
  }
}

export function fetchOrderStats() {
  return dispatch =>
    axios
      .get("/stats/internal/orders")
      .then(response => {
        return dispatch({
          type: STATS_LOADED,
          payload: response.data,
        });
      }
      );
}
