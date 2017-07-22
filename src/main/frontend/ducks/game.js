import { GAME } from './ActionTypes';

export default function game(state = null, action) {
    const { type, payload } = action;

    switch (type) {
        case GAME.REQUEST:
            return null;
        case GAME.SUCCESS:
            return payload;
        case GAME.ERROR:
            return false;

        default:
            return state;
    }
}

import fetch from 'utils/fetch';

export function fetchGameStatus(fieldId) {
    return (dispatch) => {
        dispatch({ type: GAME.REQUEST });

        return fetch(`/api/field/${fieldId}/status`, {
            method: 'GET',
            headers: { 'Accept': 'application/json' }
        })
            .catch(error => {
                dispatch({ type: GAME.ERROR });

                throw error;
            })
            .then(result => {
                dispatch({ type: GAME.SUCCESS, payload: result });

                return result;
            });
    };
}