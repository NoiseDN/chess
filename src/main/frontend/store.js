import { createStore, applyMiddleware, compose} from 'redux';
import createLogger from 'redux-logger';
import thunkMiddleware from 'redux-thunk';

import reducer from 'ducks';

const middlewares = [ thunkMiddleware ];

if (DEBUG) {
    middlewares.push(createLogger({
        actionTransformer: (action) => ({
            ...action,
            type: String(action.type)
        })
    }));
}

const store = createStore(reducer, void 0, compose(applyMiddleware(...middlewares)));

export default store;