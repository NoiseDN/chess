import { combineReducers } from 'redux';

import field from 'ducks/field';
import moves from 'ducks/moves';

export default combineReducers({
    field,
    moves
});