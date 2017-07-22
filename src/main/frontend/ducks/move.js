import { MOVE } from './ActionTypes';

export default function move(state = null, action) {
    const { type } = action;

    switch (type) {
        case MOVE.REQUEST:
            return null;
        case MOVE.SUCCESS:
            return true;
        case MOVE.ERROR:
            return false;

        default:
            return state;
    }
}

import fetch from 'utils/fetch';

export function moveFigure(figureId, moveType, coordinates) {
    return (dispatch) => {
        dispatch({ type: MOVE.REQUEST });

        return fetch(`/api/move/${figureId}`, {
            method: 'PUT',
            body: JSON.stringify({
                moveType,
                coordinates
            })
        })
            .catch(error => {
                dispatch({ type: MOVE.ERROR });

                throw error;
            })
            .then(result => {
                dispatch({ type: MOVE.SUCCESS });

                return result;
            });
    };
}