package com.anson.abc.http.parser;

import com.anson.abc.http.request.IRequest;
import com.anson.abc.http.response.IResponse;
import com.anson.abc.http.result.IResult;

/**
 * Created by anson on 2019/1/26.
 */
public interface IParser {

    IResult parseResponse(IRequest request, IResponse response);
}
