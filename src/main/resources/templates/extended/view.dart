import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

import 'provider.dart';
import 'state.dart';

class $namePage extends StatelessWidget {
  late final $nameProvider mProvider;
  late final $nameState mState;

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (BuildContext context) => $nameProvider(),
      child: Selector<$nameProvider, $nameProvider>(
        shouldRebuild: (previous, next) => false,
        selector: (context, provider) => provider,
        builder: (context, provider, child) {
          mProvider = provider;
          mState = provider.state;
          return _buildPage(context);
        },
      ),
    );
  }

  Widget _buildPage(BuildContext context) {
    return Container();
  }
}