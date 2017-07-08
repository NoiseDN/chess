import { FIELDS } from './ActionTypes';

export default function fields(state = null, action) {
    const { type, payload } = action;

    switch (type) {
        case FIELDS.REQUEST:
            return null;
        case FIELDS.SUCCESS:
            return payload;
        case FIELDS.ERROR:
            return new Error(payload);

        default:
            return state;
    }
}

import fetch from 'utils/fetch';

export function fetchFields() {
    return (dispatch) => {
        dispatch({ type: FIELDS.REQUEST });

        return fetch(`/api/field`, {
            headers: { 'Accept': 'application/json' }
        })
            .catch(error => {
                dispatch({ type: FIELDS.ERROR, payload: error });

                throw error;
            })
            .then(result => {
                dispatch({ type: FIELDS.SUCCESS, payload: result});

                return result;
            });
    };
}