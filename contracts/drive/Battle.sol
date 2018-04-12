pragma solidity ^0.4.21;
import "../oraclizeAPI/oraclizeAPI.sol";


contract Battle is usingOraclize {

    event RandomGenerated(uint result);
    
    uint8 public constant MAX_TRY_COUNT = 10;
    uint private result;
    // number of random bytes we want the datasource to return
    uint8 private n;

    function Battle(uint8 _n) public {
        n = _n;
        // sets the Ledger authenticity proof in the constructor
        oraclize_setProof(proofType_Ledger); 
        generateRandom();
    }

    // the callback function is called by Oraclize when the result is ready
    // the oraclize_randomDS_proofVerify modifier prevents an invalid proof to execute this function code:
    // the proof validity is fully verified on-chain
    function __callback(bytes32 _queryId, string _result, bytes _proof) { 
        require(msg.sender == oraclize_cbAddress());
        
        if (oraclize_randomDS_proofVerify__returnCode(_queryId, _result, _proof) != 0) {
            // the proof verification has failed, do we need to take any action here? (depends on the use case)
        } else {
            // the proof verification has passed
            // now that we know that the random number was safely generated, let's use it..
            
            // bytes(_result); // this is the resulting random number (bytes)
            
            // for simplicity of use, let's also convert the random bytes to uint if we need
            /* this is the highest uint we want to get. 
               It should never be greater than 2^(8*N), 
               where N is the number of random bytes we had asked the datasource to return
            */
            uint maxRange = 2 ** (8 * n); 
            // this is an efficient way to get the uint out in the [0, maxRange] range
            result = uint(keccak256(_result)) % maxRange; 
            
            emit RandomGenerated(result); // this is the resulting random number (uint)
        }
    }

    function generateRandom() public payable {
        // number of seconds to wait before the execution takes place
        uint delay = 0; 
        // amount of gas we want Oraclize to set for the callback function
        uint callbackGas = 200000;
        // this function internally generates the correct oraclize_query and returns its queryId 
        bytes32 queryId = oraclize_newRandomDSQuery(delay, n, callbackGas); 
    }

    
}