import { MOVES } from './ActionTypes';

export default function moves(state = null, action) {
    const { type, payload } = action;

    switch (type) {
        case MOVES.REQUEST:
            return null;
        case MOVES.SUCCESS:
            return payload;
        case MOVES.ERROR:
            return new Error(payload);

        default:
            return state;
    }
}

import fetch from 'utils/fetch';

export function getPossibleMoves(figure) {
    return (dispatch) => {
        dispatch({ type: MOVES.REQUEST });

        return fetch('/api/moves', {
            method: 'POST',
            headers: { 'Accept': 'application/json' },
            body: JSON.stringify(figure)
        })
            .catch(error => {
                dispatch({ type: MOVES.ERROR, payload: error });

                throw error;
            })
            .then(result => {
                dispatch({ type: MOVES.SUCCESS, payload: result});

                return result;
            });
    };
}