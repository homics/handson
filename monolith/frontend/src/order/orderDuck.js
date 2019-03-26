import axios from 'axios';

const initialState = {
  currentOrder: {},
  orders: [],
};
const CURRENT_ORDER_LOADED = 'CURRENT_ORDER_LOADED';
const CURRENT_ORDER_MODIFY = 'CURRENT_ORDER_MODIFY';
const ORDERS_LOADED = 'ORDERS_LOADED';

export default function order(state = initialState, action) {
  switch (action.type) {
  case CURRENT_ORDER_LOADED:
    return {
      ...state,
      currentOrder: action.payload,
    };
  case ORDERS_LOADED:
    return {
      ...state,
      orders: action.payload,
    };
  case CURRENT_ORDER_MODIFY:
    return state;
  default:
    return state
  }
}

export function fetchCurrentOrder() {
  return dispatch =>
    axios
      .get("/mono/internal/orders/current")
      .then(response => {
        return dispatch({
          type: CURRENT_ORDER_LOADED,
          payload: response.data,
        });
      }
      );
}

export function fetchOrdersHistory() {
  return dispatch =>
    axios
      .get("/mono/internal/orders/history").then(response =>
        dispatch({
          type: ORDERS_LOADED,
          payload: response.data,
        })
      );
}

export function addArticleToOrder(order, article) {
  return dispatch =>
    axios
      .post(`/mono/internal/orders/${order.id}/add-article`, {
        articleId: article.id,
        quantity: 1
      }).then(() =>
        dispatch(fetchCurrentOrder())
      );
}

export function deleteOrderLine(order, line) {
  return dispatch =>
    axios
      .delete(`/mono/internal/orders/${order.id}/order-lines/${line.id}`).then(() =>
        dispatch(fetchCurrentOrder())
      );
}

export function payOrder(order) {
  console.log("payment");
  return dispatch =>
    axios
      .post("/mono/internal/orders/" + order.id + "/pay").then(() =>
        dispatch({
          type: CURRENT_ORDER_MODIFY,
        })
      ).then(() =>
        dispatch(fetchCurrentOrder())
      );
}
