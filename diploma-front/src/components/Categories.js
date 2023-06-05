import React, {Component} from 'react';

class Categories extends Component {

    constructor(props) {
        super(props);
        this.state = {
            categories: [
                {
                    key: 'ALL',
                    name: 'all'
                },
                {
                    key: 'AUTO_AND_TRANSPORT',
                    name: 'transport'
                },
                {
                    key: 'ELECTRONICS',
                    name: 'electronics'
                },
                {
                    key: 'HOBBIES_SPORTS_AND_TOURISM',
                    name: 'sport and tourism'
                },
                {
                    key: 'COMPUTER_TECHNOLOGY',
                    name: 'computer technology'
                }
            ]
        }
    }

    render() {
        return (
            <div className='categories'>
                {this.state.categories.map(el => (
                    <div key={el.key} onClick={() => this.props.chooseCategory(el.key)}>{el.name}</div>
                ))}
            </div>
        );
    }
}

export default Categories;