import { createStore, applyMiddleware } from 'redux';
import createSagaMiddleware from 'redux-saga';

import rootSaga from './sagas';
import modules from './modules';

const sagaMiddleware = createSagaMiddleware();

const configure = () => {
    const devTools = window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__();
    const store = createStore(modules, applyMiddleware(sagaMiddleware));

    sagaMiddleware.run(rootSaga);
    return store;
}

export default configure;
