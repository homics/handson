import axios from 'axios';

export default function auth(state = {}, action) {
    return state
}

export function performLogout(){
  return () =>
    axios
        .post("http://localhost:8080/mono/perform_logout")
        .finally( () =>  window.location.replace("http://localhost:8080/mono/login"));
}
