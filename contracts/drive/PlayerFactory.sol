pragma solidity ^0.4.21;


contract PlayerFactory {

    event NewUser(User user);

    mapping (address => User) public addressToUser;
    
    struct User {
        uint id;
        string name;
        uint32 level;
        uint32 battleCount;
        uint16 victoryCount;
        uint16 defeatCount;
    }

    function createUser(address _address, string _name) public {
        uint id = uint(keccak256(_address));
        _createUser(_address, id, _name);
    }

    function _createUser(address _address, uint _id, string _name) internal {
        User memory user = User(_id, _name, 0, 0, 0, 0);
        NewUser(user);
        addressToUser[_address] = user;
    }

}