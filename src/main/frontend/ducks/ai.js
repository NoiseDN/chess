import { AI } from './ActionTypes';

export default function move(state = null, action) {
    const { type } = action;

    switch (type) {
        case AI.REQUEST:
            return null;
        case AI.SUCCESS:
            return true;
        case AI.ERROR:
            return false;

        default:
            return state;
    }
}

import fetch from 'utils/fetch';

export function makeAiMove(fieldId) {
    return (dispatch) => {
        dispatch({ type: AI.REQUEST });

        return fetch(`/api/ai/${fieldId}`, {
            method: 'PUT'
        })
            .catch(error => {
                dispatch({ type: AI.ERROR });

                throw error;
            })
            .then(result => {
                dispatch({ type: AI.SUCCESS });

                return result;
            });
    };
}