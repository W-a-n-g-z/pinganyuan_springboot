package com.enss.ipfsgate.utils.fisco.contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.abi.FunctionReturnDecoder;
import org.fisco.bcos.sdk.abi.TypeReference;
import org.fisco.bcos.sdk.abi.datatypes.Bool;
import org.fisco.bcos.sdk.abi.datatypes.Function;
import org.fisco.bcos.sdk.abi.datatypes.Type;
import org.fisco.bcos.sdk.abi.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.abi.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.client.Client;
import org.fisco.bcos.sdk.contract.Contract;
import org.fisco.bcos.sdk.crypto.CryptoSuite;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.model.CryptoType;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class FiatShamir extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405261204d600055600b60015534801561001b57600080fd5b5061036b8061002b6000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806321bdf79b146100885780632b14b54d146100b35780632e52d606146100e057806348d6f3ea1461010b578063aa4bd8e914610138578063c3da42b81461017d578063e2179b8e146101a8575b600080fd5b34801561009457600080fd5b5061009d6101d3565b6040518082815260200191505060405180910390f35b3480156100bf57600080fd5b506100de600480360381019080803590602001909291905050506101eb565b005b3480156100ec57600080fd5b506100f56101f5565b6040518082815260200191505060405180910390f35b34801561011757600080fd5b50610136600480360381019080803590602001909291905050506101fb565b005b34801561014457600080fd5b5061016360048036038101908080359060200190929190505050610205565b604051808215151515815260200191505060405180910390f35b34801561018957600080fd5b50610192610249565b6040518082815260200191505060405180910390f35b3480156101b457600080fd5b506101bd61024f565b6040518082815260200191505060405180910390f35b60006101dd610255565b600381905550600354905090565b8060028190555050565b60005481565b8060048190555050565b600080600090506000546102206002546003546000546102ee565b61022f600154866000546102ee565b0281151561023957fe5b0690508060045414915050919050565b60035481565b60015481565b6000805442604051602001808281526020019150506040516020818303038152906040526040518082805190602001908083835b6020831015156102ae5780518252602082019150602081019050602083039250610289565b6001836020036101000a0380198251168184511680821785525050505050509050019150506040518091039020600190048115156102e857fe5b06905090565b600060405160208152602080820152602060408201528460608201528360808201528260a082015260c05160208160c08460006005600019f1151561033257600080fd5b80519250505093925050505600a165627a7a723058208d9ea29d37a1a31582495bbe125f375cc328958e634e7fa662916db7f7f259df0029"};

    public static final String BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405261204d600055600b60015534801561001b57600080fd5b5061036b8061002b6000396000f300608060405260043610610083576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff1680633edfe8f2146100885780635f8d0207146100b3578063658f6b97146100e0578063a414114614610125578063b763b9b614610150578063ee3ac8631461017b578063fa5d91fe146101a6575b600080fd5b34801561009457600080fd5b5061009d6101d3565b6040518082815260200191505060405180910390f35b3480156100bf57600080fd5b506100de600480360381019080803590602001909291905050506101d9565b005b3480156100ec57600080fd5b5061010b600480360381019080803590602001909291905050506101e3565b604051808215151515815260200191505060405180910390f35b34801561013157600080fd5b5061013a610227565b6040518082815260200191505060405180910390f35b34801561015c57600080fd5b5061016561022d565b6040518082815260200191505060405180910390f35b34801561018757600080fd5b50610190610233565b6040518082815260200191505060405180910390f35b3480156101b257600080fd5b506101d16004803603810190808035906020019092919050505061024b565b005b60005481565b8060028190555050565b600080600090506000546101fe600254600354600054610255565b61020d60015486600054610255565b0281151561021757fe5b0690508060045414915050919050565b60035481565b60015481565b600061023d6102a6565b600381905550600354905090565b8060048190555050565b600060405160208152602080820152602060408201528460608201528360808201528260a082015260c05160208160c08460006005600019f1151561029957600080fd5b8051925050509392505050565b6000805442604051602001808281526020019150506040516020818303038152906040526040518082805190602001908083835b6020831015156102ff57805182526020820191506020810190506020830392506102da565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206001900481151561033957fe5b069050905600a165627a7a72305820a724a115f781fe63ccb42f4adcd418273744ec07dd5ea9b45329a5d49bef67e50029"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"constant\":false,\"inputs\":[],\"name\":\"Step3_randomchallenge\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_y\",\"type\":\"uint256\"}],\"name\":\"Step1_register\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"n\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"_t\",\"type\":\"uint256\"}],\"name\":\"Step2_login\",\"outputs\":[],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":false,\"inputs\":[{\"name\":\"r\",\"type\":\"uint256\"}],\"name\":\"Step45_verify\",\"outputs\":[{\"name\":\"\",\"type\":\"bool\"}],\"payable\":false,\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"c\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"},{\"constant\":true,\"inputs\":[],\"name\":\"g\",\"outputs\":[{\"name\":\"\",\"type\":\"uint256\"}],\"payable\":false,\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_STEP3_RANDOMCHALLENGE = "Step3_randomchallenge";

    public static final String FUNC_STEP1_REGISTER = "Step1_register";

    public static final String FUNC_N = "n";

    public static final String FUNC_STEP2_LOGIN = "Step2_login";

    public static final String FUNC_STEP45_VERIFY = "Step45_verify";

    public static final String FUNC_C = "c";

    public static final String FUNC_G = "g";

    protected FiatShamir(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public TransactionReceipt Step3_randomchallenge() {
        final Function function = new Function(
                FUNC_STEP3_RANDOMCHALLENGE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

//    public byte[] Step3_randomchallenge(TransactionCallback callback) {
//        final Function function = new Function(
//                FUNC_STEP3_RANDOMCHALLENGE,
//                Arrays.<Type>asList(),
//                Collections.<TypeReference<?>>emptyList());
//        return asyncExecuteTransaction(function, callback);
//    }

    public String getSignedTransactionForStep3_randomchallenge() {
        final Function function = new Function(
                FUNC_STEP3_RANDOMCHALLENGE, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getStep3_randomchallengeOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_STEP3_RANDOMCHALLENGE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt Step1_register(BigInteger _y) {
        final Function function = new Function(
                FUNC_STEP1_REGISTER, 
                Arrays.<Type>asList(new Uint256(_y)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

//    public byte[] Step1_register(BigInteger _y, TransactionCallback callback) {
//        final Function function = new Function(
//                FUNC_STEP1_REGISTER,
//                Arrays.<Type>asList(new Uint256(_y)),
//                Collections.<TypeReference<?>>emptyList());
//        return asyncExecuteTransaction(function, callback);
//    }

    public String getSignedTransactionForStep1_register(BigInteger _y) {
        final Function function = new Function(
                FUNC_STEP1_REGISTER, 
                Arrays.<Type>asList(new Uint256(_y)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getStep1_registerInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_STEP1_REGISTER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public BigInteger n() throws ContractException {
        final Function function = new Function(FUNC_N, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt Step2_login(BigInteger _t) {
        final Function function = new Function(
                FUNC_STEP2_LOGIN, 
                Arrays.<Type>asList(new Uint256(_t)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

//    public byte[] Step2_login(BigInteger _t, TransactionCallback callback) {
//        final Function function = new Function(
//                FUNC_STEP2_LOGIN,
//                Arrays.<Type>asList(new Uint256(_t)),
//                Collections.<TypeReference<?>>emptyList());
//        return asyncExecuteTransaction(function, callback);
//    }

    public String getSignedTransactionForStep2_login(BigInteger _t) {
        final Function function = new Function(
                FUNC_STEP2_LOGIN, 
                Arrays.<Type>asList(new Uint256(_t)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getStep2_loginInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_STEP2_LOGIN, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public TransactionReceipt Step45_verify(BigInteger r) {
        final Function function = new Function(
                FUNC_STEP45_VERIFY, 
                Arrays.<Type>asList(new Uint256(r)),
                Collections.<TypeReference<?>>emptyList());
        return executeTransaction(function);
    }

//    public byte[] Step45_verify(BigInteger r, TransactionCallback callback) {
//        final Function function = new Function(
//                FUNC_STEP45_VERIFY,
//                Arrays.<Type>asList(new Uint256(r)),
//                Collections.<TypeReference<?>>emptyList());
//        return asyncExecuteTransaction(function, callback);
//    }

    public String getSignedTransactionForStep45_verify(BigInteger r) {
        final Function function = new Function(
                FUNC_STEP45_VERIFY, 
                Arrays.<Type>asList(new Uint256(r)),
                Collections.<TypeReference<?>>emptyList());
        return createSignedTransaction(function);
    }

    public Tuple1<BigInteger> getStep45_verifyInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_STEP45_VERIFY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<BigInteger>(

                (BigInteger) results.get(0).getValue()
                );
    }

    public Tuple1<Boolean> getStep45_verifyOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function = new Function(FUNC_STEP45_VERIFY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        List<Type> results = FunctionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>(

                (Boolean) results.get(0).getValue()
                );
    }

    public BigInteger c() throws ContractException {
        final Function function = new Function(FUNC_C, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger g() throws ContractException {
        final Function function = new Function(FUNC_G, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public static FiatShamir load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new FiatShamir(contractAddress, client, credential);
    }

    public static FiatShamir deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(FiatShamir.class, client, credential, getBinary(client.getCryptoSuite()), "");
    }
}
