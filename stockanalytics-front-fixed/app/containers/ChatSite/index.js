import React from 'react';
import { compose } from 'redux';
import { connect } from 'react-redux';
import { createStructuredSelector } from 'reselect';
import injectReducer from 'utils/injectReducer';
import injectSaga from 'utils/injectSaga';
import { slide as Menu } from 'react-burger-menu';
import reducer from './reducer';
import saga from './saga';
import makeSelectStocks from './selectors';
import ChatRoom from '../../components/Chat/ChatRoom';
import Chat from '../../components/Chat/Chat';

class ChatSite extends React.PureComponent {
  constructor(props) {
    super(props);

    this.state = {
      chatActivated: false,
    };
  }

  changeActivation = () => {
    this.setState({
      chatActivated: true,
    });
  };

  isMenuOpen = ({ isOpen }) => {
    if (!isOpen)
      this.setState({
        chatActivated: false,
      });
  };

  render() {
    return (
      <Menu
        onStateChange={this.isMenuOpen}
        width="375px"
        right
        burgerButtonClassName="far fa-comments chat"
        customBurgerIcon={<i className="far fa-comments" />}
      >
        {!this.state.chatActivated ? (
          <ChatRoom goclick={this.changeActivation} />
        ) : (
          <Chat />
        )}
      </Menu>
    );
  }
}

ChatSite.propTypes = {};

export function mapDispatchToProps(dispatch) {
  return {
    onLoadDefaultStocks: () => dispatch(),
  };
}

const mapStateToProps = createStructuredSelector({
  stocks: makeSelectStocks,
});

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'chat', reducer });
const withSaga = injectSaga({ key: 'chat', saga });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(ChatSite);
