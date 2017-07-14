import { combineReducers } from 'redux';

import field from 'ducks/field';
import fields from 'ducks/fields';
import moves from 'ducks/moves';
import move from 'ducks/move';

export default combineReducers({
    field,
    fields,
    moves,
    move
});