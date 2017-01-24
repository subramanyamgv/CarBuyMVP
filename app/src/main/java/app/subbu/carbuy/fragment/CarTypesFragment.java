package app.subbu.carbuy.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import app.subbu.carbuy.R;
import app.subbu.carbuy.activity.CarTypesActivity;
import app.subbu.carbuy.activity.EntitySelectionListener;
import app.subbu.carbuy.adapter.CarTypesListAdapter;
import app.subbu.carbuy.entity.Manufacturer;
import app.subbu.carbuy.injector.component.CarSelectionComponent;
import app.subbu.carbuy.utils.ErrorUtils;
import app.subbu.mvp.model.CarTypes;
import app.subbu.mvp.presenter.CarTypesPresenter;
import app.subbu.mvp.view.CarTypesView;
import app.subbu.repository.Repository;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Subramanyam on 22-Jan-2017.
 */

public class CarTypesFragment extends Fragment implements CarTypesView,
        BaseQuickAdapter.RequestLoadMoreListener {

    public static final String TAG = "CarTypesFragment";

    @Inject
    CarTypesPresenter mPresenter;

    @BindView(R.id.list)
    RecyclerView mRecyclerView;

    private EntitySelectionListener<Manufacturer> selectionListener;
    private CarTypesListAdapter mAdapter;
    private int currentPage = 0;

    public static CarTypesFragment newInstance() {
        return new CarTypesFragment();
    }

    public static void start(int contentViewId, FragmentManager fragmentManager) {
        CarTypesFragment fragment = newInstance();
        fragmentManager.beginTransaction().replace(contentViewId, fragment,
                TAG).commit();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        mPresenter.onCreate();
    }

    private void injectDependencies() {
        if (getActivity() instanceof CarTypesActivity) {
            CarTypesActivity activity = (CarTypesActivity) getActivity();
            CarSelectionComponent carSelectionComponent = activity.getCarSelectionComponent();
            carSelectionComponent.inject(this);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_types, container, false);
        ButterKnife.bind(this, view);

        //Init Adapter
        mAdapter = new CarTypesListAdapter(new LinkedList<Manufacturer>());

        //Init List
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        //Init Presenter
        mPresenter.attachView(this);

        //Set Load more call back
        mAdapter.setOnLoadMoreListener(this);

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Manufacturer manufacturer = (Manufacturer) adapter.getItem(position);
                selectionListener.onEntitySelected(manufacturer);
            }
        });

        mPresenter.getCarTypes(0, Repository.DEFAULT_PAGE_SIZE);

        return view;
    }

    @Override
    public void onLoadMoreRequested() {
        mPresenter.getCarTypes(currentPage + 1, Repository.DEFAULT_PAGE_SIZE);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            selectionListener = (EntitySelectionListener)getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString() + " should implement EntitySelectionListener");
        }
    }

    public void addCarTypes(CarTypes carTypes) {

        List<Manufacturer> data = new LinkedList<>();
        int itemType = 0;

        for (Map.Entry<String, String> entry : carTypes.getWkda().entrySet()) {
            Manufacturer carType = new Manufacturer(itemType, entry.getKey(), entry.getValue());
            data.add(carType);
            itemType = itemType ^ 1;
        }

        currentPage = carTypes.getPage();
        mAdapter.addData(data);

        if (currentPage == carTypes.getTotalPageCount())
            mAdapter.loadMoreEnd(true);
        else
            mAdapter.loadMoreComplete();
    }

    @Override
    public void showLoading() {

        if (!isAdded())
            return;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAdapter.setEmptyView(R.layout.loading_view,
                        (ViewGroup) mRecyclerView.getParent());
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showEmpty() {

        if (!isAdded())
            return;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                mAdapter.setEmptyView(R.layout.empty_view,
                        (ViewGroup) mRecyclerView.getParent());
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showCarTypes(final CarTypes carTypes) {

        if (!isAdded())
            return;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                addCarTypes(carTypes);
            }
        };

        getActivity().runOnUiThread(runnable);
    }

    @Override
    public void showError(Throwable e) {

        if (!isAdded())
            return;

        final ErrorUtils.ErrorType error = ErrorUtils.getErrorType(e);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                mAdapter.setEmptyView(R.layout.error_view,
                        (ViewGroup) mRecyclerView.getParent());

                ((TextView)mAdapter.getEmptyView()
                        .findViewById(R.id.error_text))
                        .setText(error.getRes());
            }
        };

        getActivity().runOnUiThread(runnable);
    }
}
