import React from 'react';
import PropTypes from 'prop-types';

const Message = ({ message, author }) => (
  <div>
    <div className="head">
      <span className="name">{author}</span>
    </div>
    <div className="body">
      <span className="time">{new Date().toLocaleDateString()}</span>
      <div className="msg">{message}</div>
    </div>
  </div>
);

Message.propTypes = {
  message: PropTypes.string,
  author: PropTypes.string,
};

export default Message;
