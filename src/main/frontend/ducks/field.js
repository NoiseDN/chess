import { FIELD } from './ActionTypes';

export default function field(state = null, action) {
    const { type, payload } = action;

    switch (type) {
        case FIELD.REQUEST:
            return null;
        case FIELD.SUCCESS:
            return payload;
        case FIELD.ERROR:
            return new Error(payload);

        default:
            return state;
    }
}

import fetch from 'utils/fetch';

export function fetchField(id) {
    return (dispatch) => {
        dispatch({ type: FIELD.REQUEST });

        return fetch(`/api/field/${id}`, {
            headers: { 'Accept': 'application/json' }
        })
            .catch(error => {
                dispatch({ type: FIELD.ERROR, payload: error });

                throw error;
            })
            .then(result => {
                dispatch({ type: FIELD.SUCCESS, payload: result});

                return result;
            });
    };
}

export function createField(playWhites) {
    return (dispatch) => {
        dispatch({ type: FIELD.REQUEST });

        return fetch(`/api/field/${playWhites ? 'white' : 'black'}`, {
            method: 'POST',
            headers: { 'Accept': 'application/json' }
        })
            .catch(error => {
                dispatch({ type: FIELD.ERROR, payload: error });

                throw error;
            })
            .then(result => {
                dispatch({ type: FIELD.SUCCESS, payload: result});

                return result;
            });
    };
}