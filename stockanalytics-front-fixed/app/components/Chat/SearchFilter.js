import React from 'react';

class SearchFilter extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      searchString: '',
    };
  }

  handleChange = e => {};

  render() {
    return (
      <div>
        <input
          type="text"
          value={this.state.searchString}
          onChange={this.handleChange}
          placeholder="채팅방을 검색하세요..."
        />
      </div>
    );
  }
}

export default SearchFilter;
