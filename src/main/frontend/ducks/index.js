import { combineReducers } from 'redux';

import field from 'ducks/field';
import fields from 'ducks/fields';
import moves from 'ducks/moves';

export default combineReducers({
    field,
    fields,
    moves
});