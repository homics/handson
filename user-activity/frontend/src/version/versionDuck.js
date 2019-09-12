import axios from 'axios';

const initialState = {
    versions: [],
};
const VERSION_LOADED = 'VERSION_LOADED';
let fetchVersionLoop;

export default function version(state = initialState, action) {
    switch (action.type) {
        case VERSION_LOADED:
            let versions = state.versions;
            versions.unshift(action.payload);
            return {...state, versions: versions.slice(0, 200)};
        default:
            return state
    }
}

function fetchVersion(dispatch) {
    axios
        .get("/user/internal/version")
        .then(response =>
            dispatch({
                type: VERSION_LOADED,
                payload: response.data,
            })
        );
}

export function fetchVersions() {
    return dispatch => {
        if (fetchVersionLoop) {
            clearInterval(fetchVersionLoop);
        }
        // To start the loop
        fetchVersionLoop = setInterval(function () {
            fetchVersion(dispatch)
        }, 1000);
    }
}
