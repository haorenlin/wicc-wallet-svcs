package com.waykichain.coin.wicc;

import com.alibaba.fastjson.JSONObject;
import com.waykichain.JsonRpcClient;
import com.waykichain.JsonRpcRequest;
import com.waykichain.coin.wicc.po.*;
import com.waykichain.coin.wicc.vo.*;
import com.waykichain.serialize.AnnotationsConfigurationsLoader;
import com.waykichain.serialize.ConfigurationsLoader;
import com.waykichain.serialize.DataABIEncoder;
import com.waykichain.utils.RandomUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Joss
 *
 * @since 0.1.0
 */
@Slf4j
public class WiccMethods {
    @Setter
    private JsonRpcClient rpcClient;
    @Setter
    private DataABIEncoder dataAbiEncoder;

    private WiccMethods() {
        super();
    }

    public WiccMethods(JsonRpcClient rpcClient) throws Exception {
        super();
        this.rpcClient = rpcClient;
        ConfigurationsLoader condigurationsLoader;
        condigurationsLoader = new AnnotationsConfigurationsLoader();
        condigurationsLoader.setPackageToScan("com.waykichain.serialize");
//        condigurationsLoader.loadConfigurations();
        this.dataAbiEncoder = new DataABIEncoder(condigurationsLoader);
    }

    public <T, U> T call(String methodId, U data, Class<T> clazz) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_CALL);

        request.getParams().add(this.dataAbiEncoder.encode(methodId, data));
        request.getParams().add("latest");

        return this.execute(request, clazz);
    }


    public WiccAddressJsonRpcResponse getNewAddress() throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_NEW_ADDRESS);
        WiccAddressJsonRpcResponse reponse =  this.execute(request, WiccAddressJsonRpcResponse.class);
        return reponse;
    }

    public WiccBalanceJsonRpcResponse getBalance(String address) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_BALANCE);
        request.getParams().add(address);
        WiccBalanceJsonRpcResponse reponse =  this.execute(request, WiccBalanceJsonRpcResponse.class);
        return reponse;
    }

    public WiccAccountInfoJsonRpcResponse getAccountInfo(String address) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_ACCOUNT_INFO);
        request.getParams().add(address);
        WiccAccountInfoJsonRpcResponse reponse =  this.execute(request, WiccAccountInfoJsonRpcResponse.class);
        return reponse;
    }

    public WiccSubmitTxJsonRpcResponse activeAddress(String address) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_REGIST_ACOUNT_TX);
        request.getParams().add(address);
        request.getParams().add(100000);
        WiccSubmitTxJsonRpcResponse reponse =  this.execute(request, WiccSubmitTxJsonRpcResponse.class);
        return reponse;
    }


    public WiccInfoJsonRpcResponse getInfo() throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_INFO);
        WiccInfoJsonRpcResponse reponse =  this.execute(request, WiccInfoJsonRpcResponse.class);
        return reponse;
    }

    public WiccListAddressJsonRpcResponse listAddress() throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_LIST_ADDR);
        WiccListAddressJsonRpcResponse reponse =  this.execute(request, WiccListAddressJsonRpcResponse.class);
        return reponse;
    }

    public WiccGetTxDetailJsonRpcResponse getTxDetail(String tx) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_TX_DETAIL);
        request.getParams().add(tx);
        WiccGetTxDetailJsonRpcResponse reponse =  this.execute(request, WiccGetTxDetailJsonRpcResponse.class);
        return reponse;
    }

    public WiccBlockJsonRpcResponse getBlock(Integer blockId) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_BLOCK);
        request.getParams().add(blockId);
        request.getParams().add(true);
        WiccBlockJsonRpcResponse reponse =  this.execute(request, WiccBlockJsonRpcResponse.class);
        return reponse;
    }

    public WiccSubmitTxJsonRpcResponse sendToAddress(SendToAddressPO sendToAddressPO) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SEND_TO_ADDRESS);
        request.getParams().add(sendToAddressPO.sendAddress);
        request.getParams().add(sendToAddressPO.recvAddress);
        request.getParams().add(sendToAddressPO.amount);
        WiccSubmitTxJsonRpcResponse reponse =  this.execute(request, WiccSubmitTxJsonRpcResponse.class);
        return reponse;
    }

    public WiccRawTxJsonRpcResponse sendToAddressRaw(SendToAddressRawPO sendToAddressRawPO)throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SEND_TO_ADDRESS_RAW);
        request.getParams().add(sendToAddressRawPO.getFee());
        request.getParams().add(sendToAddressRawPO.getAmount());
        request.getParams().add(sendToAddressRawPO.getSrcaddress());
        request.getParams().add(sendToAddressRawPO.getRecvaddress());
        request.getParams().add(sendToAddressRawPO.getHeight());
        WiccRawTxJsonRpcResponse reponse =  this.execute(request, WiccRawTxJsonRpcResponse.class);
        return reponse;
    }

    public WiccSubmitTxJsonRpcResponse submitTx(String tx) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_SUBMIT_TX);
        request.getParams().add(tx);
        WiccSubmitTxJsonRpcResponse reponse =  this.execute(request, WiccSubmitTxJsonRpcResponse.class);
        return reponse;
    }

    public WiccTxHashJsonRpcResponse registerAppTx(RegisterAppTxPO registerAppTxPO) throws Exception {
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_REGISTER_APP_TX);
        request.getParams().add(registerAppTxPO.getAddr());
        request.getParams().add(registerAppTxPO.getFilepath());
        request.getParams().add(registerAppTxPO.getFee());
        WiccTxHashJsonRpcResponse response = this.execute(request, WiccTxHashJsonRpcResponse.class);
        return response;
    }

    public WiccGetScriptIdJsonRpcResponse getScriptId(GetScriptIdPO getScriptIdPO) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_SCRIPT_ID);
        request.getParams().add(getScriptIdPO.getHash());
        WiccGetScriptIdJsonRpcResponse response = this.execute(request, WiccGetScriptIdJsonRpcResponse.class);
        return response;
    }

    public WiccCreateContractTxJsonRpcResponse WiccCreateContractTx(CreateContractTxPO createContractTxPO) throws Exception {
    	JsonRpcRequest request = new JsonRpcRequest();
    	request.setId(RandomUtils.generateRandomId());
    	request.setMethod(WiccOperationType.METHOD_CREATE_CONTRACT_TX);
    	request.getParams().add(createContractTxPO.getUserregid());
    	request.getParams().add(createContractTxPO.getAppId());
    	request.getParams().add(createContractTxPO.getAmount());
    	if(createContractTxPO.getContract().length()>=2000){
    	    throw new Exception("The contract command length should less than 2000");
        }
    	request.getParams().add(createContractTxPO.getContract());
    	request.getParams().add(createContractTxPO.getFee());
    	if(createContractTxPO.getHeight()!=null) {
    		request.getParams().add(createContractTxPO.getHeight());
    	}
    	WiccCreateContractTxJsonRpcResponse response = this.execute(request, WiccCreateContractTxJsonRpcResponse.class);
    	return response;
    }

    public WiccRawTxJsonRpcResponse createContractTxRaw(CreateContractTxRawPO createContractTxRawPO) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_CREATE_CONTRACT_TX_RAW);
        request.getParams().add(createContractTxRawPO.getFee());
        request.getParams().add(createContractTxRawPO.getAmount());
        request.getParams().add(createContractTxRawPO.getAddr());
        request.getParams().add(createContractTxRawPO.getAppid());
        request.getParams().add(createContractTxRawPO.getContract());
        request.getParams().add(createContractTxRawPO.getHeight());
        WiccRawTxJsonRpcResponse response =  this.execute(request, WiccRawTxJsonRpcResponse.class);
        return response;
    }

    public WiccGetScriptDataJsonRpcResponse getScriptData(GetScriptDataPO getScriptDataPO) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_SCRIPT_DATA);
        request.getParams().add(getScriptDataPO.getScriptid());
        request.getParams().add(getScriptDataPO.getKey());
        WiccGetScriptDataJsonRpcResponse response =this.execute(request, WiccGetScriptDataJsonRpcResponse.class);
        return response;
    }

    public WiccGetAppAccInfoJsonRpcResponse getappaccinfo(GetAppAccInfoPO po) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_APP_ACC_INFO);
        request.getParams().add(po.getScriptid());
        request.getParams().add(po.getAddress());
        WiccGetAppAccInfoJsonRpcResponse response =this.execute(request, WiccGetAppAccInfoJsonRpcResponse.class);
        return response;
    }

    public WiccGetAppAccInfoJsonRpcResponse active(String address) throws Exception{
        JsonRpcRequest request = new JsonRpcRequest();
        request.setId(RandomUtils.generateRandomId());
        request.setMethod(WiccOperationType.METHOD_GET_APP_ACC_INFO);
        request.getParams().add(address);
        WiccGetAppAccInfoJsonRpcResponse response =this.execute(request, WiccGetAppAccInfoJsonRpcResponse.class);
        return response;
    }

    
    private <T> T execute(final JsonRpcRequest request, final Class<T> classTypeResponse) throws Exception {
        T response = null;
        try {

            response = this.rpcClient.execute(request, classTypeResponse);

        } catch (IOException ioExc) {
            log.error("Error calling method {}", request.getMethod(), ioExc);
            throw new Exception();
        }
        return response;
    }

}
